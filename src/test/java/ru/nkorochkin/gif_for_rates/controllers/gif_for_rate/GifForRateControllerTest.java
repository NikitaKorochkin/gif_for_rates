package ru.nkorochkin.gif_for_rates.controllers.gif_for_rate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import ru.nkorochkin.gif_for_rates.clients.download.DownloadClient;
import ru.nkorochkin.gif_for_rates.clients.exchange.ExchangeClient;
import ru.nkorochkin.gif_for_rates.clients.giphy.GiphyClient;
import ru.nkorochkin.gif_for_rates.configuration.GiphyConfig;
import ru.nkorochkin.gif_for_rates.configuration.OpenexchangeConfig;
import ru.nkorochkin.gif_for_rates.dto.exchange.ExchangeRate;
import ru.nkorochkin.gif_for_rates.dto.gif.GiphyGif;

import java.net.URI;
import java.time.LocalDate;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.nkorochkin.gif_for_rates.util.DateFormattingUtils.LOCAL_DATE_FORMATTER;

@SpringBootTest
@AutoConfigureMockMvc
class GifForRateControllerTest {

    @MockBean
    private GiphyClient giphyClient;

    @MockBean
    private ExchangeClient exchangeClient;

    @MockBean
    private DownloadClient downloadClient;

    @Autowired
    private GiphyConfig giphyConfig;

    @Autowired
    private OpenexchangeConfig openexchangeConfig;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void should_fail_for_unknown_code() throws Exception {
        String incorrectCode = "RU";
        mockMvc.perform(get("/gif-for-rate/gif").param("code", incorrectCode))
                .andDo(print())
                .andExpect(status().is(400))
                .andExpect(jsonPath("info", is("Your code: " + incorrectCode + ". Currency code must include 3 chars")));
    }

    @Test
    public void should_get_rich_gif_after_rate_increase() throws Exception {
        LocalDate now = LocalDate.now();

        ExchangeRate exchangeRateToday = new ExchangeRate();
        exchangeRateToday.setRates(Map.of("RUB", 100.0));
        when(exchangeClient.getRateFromDate(LOCAL_DATE_FORMATTER.format(now), openexchangeConfig.getAppId()))
                .thenReturn(exchangeRateToday);

        ExchangeRate exchangeRateYesterday = new ExchangeRate();
        exchangeRateYesterday.setRates(Map.of("RUB", 50.0));
        when(exchangeClient.getRateFromDate(LOCAL_DATE_FORMATTER.format(now.minusDays(1)), openexchangeConfig.getAppId()))
                .thenReturn(exchangeRateYesterday);

        String gifUrl = "some_url.gif";
        GiphyGif.GifData gifData = new GiphyGif.GifData();
        gifData.setImageOriginalUrl(gifUrl);
        GiphyGif gif = new GiphyGif(gifData);
        when(giphyClient.getRandomGifByTag(giphyConfig.getApiKey(), giphyConfig.getTagRich(), giphyConfig.getRating()))
                .thenReturn(gif);

        byte[] response = new byte[]{1, 2};
        when(downloadClient.downloadGif(URI.create(gifUrl))).thenReturn(ResponseEntity.ok(response));

        MvcResult result = mockMvc.perform(get("/gif-for-rate/gif").param("code", "RUB"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        assertThat(result.getResponse().getContentAsByteArray(), is(response));

        verify(giphyClient).getRandomGifByTag(any(), eq(giphyConfig.getTagRich()), any());
        verify(exchangeClient, times(2)).getRateFromDate(any(), any());
        verify(downloadClient).downloadGif(any());

        verify(giphyClient, never()).getRandomGifByTag(any(), eq(giphyConfig.getTagBroke()), any());
    }

}
