package ru.nkorochkin.gif_for_rates.services.gif_exchange_rate_evaluator_service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.nkorochkin.gif_for_rates.configuration.GiphyConfig;
import ru.nkorochkin.gif_for_rates.dto.gif.GiphyGif;
import ru.nkorochkin.gif_for_rates.exception_handling.incorrect_cur_code.IncorrectCurrencyCodeException;
import ru.nkorochkin.gif_for_rates.exception_handling.incorrect_url.IncorrectGifUrlException;
import ru.nkorochkin.gif_for_rates.services.exchange.ExchangeService;
import ru.nkorochkin.gif_for_rates.services.giphy.GiphyService;
import ru.nkorochkin.gif_for_rates.services.rate_evaluator.impl.GifExchangeRateEvaluatorServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class GifExchangeRateEvaluatorServiceTest {

    @InjectMocks
    private GifExchangeRateEvaluatorServiceImpl gifExchangeRateEvaluatorService;

    @Mock
    private GiphyService giphyService;

    @Mock
    private ExchangeService exchangeService;

    @Mock
    private GiphyConfig giphyConfig;

    @Test
    void should_throw_incorrect_currency_code_exception() {
        String incorrectCode = "EURS";
        Exception exception = assertThrows(
                IncorrectCurrencyCodeException.class,
                () -> gifExchangeRateEvaluatorService.getGifForCurrency("EURS")
        );
        assertEquals(exception.getMessage(), String.format("Your code: %s. Currency code must include 3 chars", incorrectCode));
    }

    @Test
    void should_throw_incorrect_gif_url_exception_when_url_is_incorrect() {
        String brokeTag = "broke_tag";
        when(giphyConfig.getTagBroke()).thenReturn(brokeTag);

        String incorrectGifUrl = "some-url.mp4";
        GiphyGif incorrectGif = new GiphyGif();
        GiphyGif.GifData data = new GiphyGif.GifData();
        data.setImageOriginalUrl(incorrectGifUrl);
        incorrectGif.setData(data);
        when(giphyService.getRandomGifByTag(brokeTag)).thenReturn(incorrectGif);

        Exception exception = assertThrows(
                IncorrectGifUrlException.class,
                () -> gifExchangeRateEvaluatorService.getGifForCurrency("USD")
        );
        assertEquals(exception.getMessage(), String.format("URL: %s. This is not a gif URL!", incorrectGifUrl));
    }

    @Test
    void should_throw_incorrect_gif_url_exception_when_url_is_null() {
        String code = "USD";
        when(exchangeService.isRateBiggerThanYesterday(code)).thenReturn(true);

        String richTag = "rich_tag";
        when(giphyConfig.getTagRich()).thenReturn(richTag);

        GiphyGif nullGif = new GiphyGif();
        nullGif.setData(new GiphyGif.GifData());
        when(giphyService.getRandomGifByTag(richTag)).thenReturn(nullGif);

        Exception exception = assertThrows(
                IncorrectGifUrlException.class,
                () -> gifExchangeRateEvaluatorService.getGifForCurrency(code)
        );
        assertEquals(exception.getMessage(), "URL: null. This is not a gif URL!");
    }
}
