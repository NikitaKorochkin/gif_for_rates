package ru.nkorochkin.gif_for_rates.exception_handling.incorrect_cur_code;

public class IncorrectCurrencyCodeException extends RuntimeException{
    public IncorrectCurrencyCodeException(String message) {
        super(message);
    }
}
