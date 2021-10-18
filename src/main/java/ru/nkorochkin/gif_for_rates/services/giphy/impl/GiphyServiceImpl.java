package ru.nkorochkin.gif_for_rates.services.giphy.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nkorochkin.gif_for_rates.clients.giphy.GiphyClient;
import ru.nkorochkin.gif_for_rates.configuration.GiphyConfig;
import ru.nkorochkin.gif_for_rates.dto.gif.GiphyGif;
import ru.nkorochkin.gif_for_rates.services.giphy.GiphyService;

@Service
@RequiredArgsConstructor
public class GiphyServiceImpl implements GiphyService {

    private final GiphyClient giphyClient;
    private final GiphyConfig giphyConfig;

    @Override
    public GiphyGif getRandomGifByTag(String tag) {
        return giphyClient.getRandomGifByTag(giphyConfig.getApiKey(), tag, giphyConfig.getRating());
    }

}
