package ru.nkorochkin.gif_for_rates.exception_handling.incorrect_url;

public class IncorrectGifUrlException extends RuntimeException {
    public IncorrectGifUrlException(String message) {
        super(message);
    }
}
