package ru.nkorochkin.gif_for_rates.exception_handling.global_handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.nkorochkin.gif_for_rates.exception_handling.incorrect_cur_code.IncorrectCurrencyCode;
import ru.nkorochkin.gif_for_rates.exception_handling.incorrect_cur_code.IncorrectCurrencyCodeException;
import ru.nkorochkin.gif_for_rates.exception_handling.incorrect_url.IncorrectGifUrl;
import ru.nkorochkin.gif_for_rates.exception_handling.incorrect_url.IncorrectGifUrlException;
import ru.nkorochkin.gif_for_rates.exception_handling.invalid_currency.InvalidCurrency;
import ru.nkorochkin.gif_for_rates.exception_handling.invalid_currency.InvalidCurrencyException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<IncorrectCurrencyCode> handleCodeEx(IncorrectCurrencyCodeException e) {
        IncorrectCurrencyCode data = new IncorrectCurrencyCode();
        data.setInfo(e.getMessage());

        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<IncorrectGifUrl> handleGifUrlEx(IncorrectGifUrlException e)   {
        IncorrectGifUrl data = new IncorrectGifUrl();
        data.setInfo(e.getMessage());

        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<InvalidCurrency> handleInvalidCurrencyEx(InvalidCurrencyException e)  {
        InvalidCurrency data = new InvalidCurrency();
        data.setInfo(e.getMessage());

        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }
}
