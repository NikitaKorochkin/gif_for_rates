package ru.nkorochkin.gif_for_rates.services.rate_evaluator;

import org.springframework.http.ResponseEntity;

public interface GifExchangeRateEvaluatorService {
    ResponseEntity<byte[]> getGifForCurrency(String currencyCode);
}
