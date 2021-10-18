package ru.nkorochkin.gif_for_rates.controllers.gif_for_rate;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.nkorochkin.gif_for_rates.services.rate_evaluator.impl.GifExchangeRateEvaluatorServiceImpl;

@RestController
@RequestMapping("gif-for-rate")
@RequiredArgsConstructor
public class GifForRateController {

    private final GifExchangeRateEvaluatorServiceImpl gifExchangeRateEvaluatorService;

    @GetMapping("gif")
    public ResponseEntity<byte[]> getRatesFromDate(@RequestParam String code) {
        return gifExchangeRateEvaluatorService.getGifForCurrency(code);
    }
}
