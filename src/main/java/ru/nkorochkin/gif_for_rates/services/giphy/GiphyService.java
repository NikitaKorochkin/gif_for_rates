package ru.nkorochkin.gif_for_rates.services.giphy;


import ru.nkorochkin.gif_for_rates.dto.gif.GiphyGif;

public interface GiphyService {
    GiphyGif getRandomGifByTag(String tag);
}
