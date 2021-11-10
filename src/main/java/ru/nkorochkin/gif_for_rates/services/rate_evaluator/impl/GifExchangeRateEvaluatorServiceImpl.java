package ru.nkorochkin.gif_for_rates.services.rate_evaluator.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.nkorochkin.gif_for_rates.configuration.GiphyConfig;
import ru.nkorochkin.gif_for_rates.dto.gif.GiphyGif;
import ru.nkorochkin.gif_for_rates.exception_handling.incorrect_cur_code.IncorrectCurrencyCodeException;
import ru.nkorochkin.gif_for_rates.exception_handling.incorrect_url.IncorrectGifUrlException;
import ru.nkorochkin.gif_for_rates.services.download.DownloadService;
import ru.nkorochkin.gif_for_rates.services.exchange.ExchangeService;
import ru.nkorochkin.gif_for_rates.services.giphy.GiphyService;
import ru.nkorochkin.gif_for_rates.services.rate_evaluator.GifExchangeRateEvaluatorService;

import java.net.URI;

@Service
@RequiredArgsConstructor
public class GifExchangeRateEvaluatorServiceImpl implements GifExchangeRateEvaluatorService {

    private final ExchangeService exchangeService;
    private final GiphyService gifService;
    private final GiphyConfig giphyConfig;
    private final DownloadService downloadService;

    private static final int CURRENCY_CODE_LENGTH = 3;

    public ResponseEntity<byte[]> getGifForCurrency(String currencyCode) {
        if (currencyCode.length() != CURRENCY_CODE_LENGTH) {
            throw new IncorrectCurrencyCodeException("Your code: " + currencyCode + ". Currency code must include 3 chars");
        }

        GiphyGif gif = exchangeService.isRateBiggerThanYesterday(currencyCode)
                ? gifService.getRandomGifByTag(giphyConfig.getTagRich())
                : gifService.getRandomGifByTag(giphyConfig.getTagBroke());

        String gifUrl = gif.getData().getImages().getOriginal().getUrl();
        if (gifUrl == null || !gifUrl.contains(".gif")) {
            throw new IncorrectGifUrlException("URL: " + gifUrl + ". This is not a gif URL!");
        }
        return downloadService.downloadGif(URI.create(gifUrl));
    }
}
