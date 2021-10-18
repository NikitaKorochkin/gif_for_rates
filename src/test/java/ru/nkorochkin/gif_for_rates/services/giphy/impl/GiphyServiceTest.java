package ru.nkorochkin.gif_for_rates.services.giphy.impl;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.nkorochkin.gif_for_rates.clients.giphy.GiphyClient;
import ru.nkorochkin.gif_for_rates.configuration.GiphyConfig;
import ru.nkorochkin.gif_for_rates.dto.gif.GiphyGif;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GiphyServiceTest {

    @InjectMocks
    private GiphyServiceImpl giphyService;

    @Mock
    private GiphyClient giphyClient;

    @Mock
    private GiphyConfig giphyConfig;

    @Test
    void should_return_gif_dto() {
        GiphyGif gif = new GiphyGif();
        GiphyGif.GifData data = new GiphyGif.GifData();
        data.setImageOriginalUrl("url");
        gif.setData(data);

        String gifApiKey = "apiKey";
        when(giphyConfig.getApiKey()).thenReturn(gifApiKey);

        String rating = "rating";
        when(giphyConfig.getRating()).thenReturn(rating);

        String tag = "some_tag";
        when(giphyClient.getRandomGifByTag(gifApiKey, tag, rating)).thenReturn(gif);

        GiphyGif resGif = giphyService.getRandomGifByTag(tag);

        assertThat(resGif, is(gif));
    }
}
