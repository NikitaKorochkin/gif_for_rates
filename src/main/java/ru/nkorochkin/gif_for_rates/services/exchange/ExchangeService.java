package ru.nkorochkin.gif_for_rates.services.exchange;

import ru.nkorochkin.gif_for_rates.dto.exchange.ExchangeRate;

import java.time.LocalDate;

public interface ExchangeService {
    ExchangeRate getRate(LocalDate date);

    boolean isRateBiggerThanYesterday(String code);
}
