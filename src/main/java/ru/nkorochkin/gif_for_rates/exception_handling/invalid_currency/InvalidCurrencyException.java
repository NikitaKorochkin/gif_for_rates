package ru.nkorochkin.gif_for_rates.exception_handling.invalid_currency;

public class InvalidCurrencyException extends RuntimeException {
    public InvalidCurrencyException(String message) {
        super(message);
    }
}
