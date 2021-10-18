package ru.nkorochkin.gif_for_rates.services.exchange.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nkorochkin.gif_for_rates.clients.exchange.ExchangeClient;
import ru.nkorochkin.gif_for_rates.configuration.OpenexchangeConfig;
import ru.nkorochkin.gif_for_rates.dto.exchange.ExchangeRate;
import ru.nkorochkin.gif_for_rates.exception_handling.invalid_currency.InvalidCurrencyException;
import ru.nkorochkin.gif_for_rates.services.exchange.ExchangeService;

import java.time.LocalDate;
import java.util.Map;

import static ru.nkorochkin.gif_for_rates.util.DateFormattingUtils.LOCAL_DATE_FORMATTER;

@Service
@RequiredArgsConstructor
public class ExchangeServiceImpl implements ExchangeService {

    private final ExchangeClient exchangeClient;
    private final OpenexchangeConfig openexchangeConfig;

    @Override
    public ExchangeRate getRate(LocalDate date) {
        String stringDate = LOCAL_DATE_FORMATTER.format(date);
        return exchangeClient.getRateFromDate(stringDate, openexchangeConfig.getAppId());
    }

    @Override
    public boolean isRateBiggerThanYesterday(String code) {
        LocalDate today = LocalDate.now();
        ExchangeRate todayRates = getRate(today);
        ExchangeRate yesterdayRates = getRate(today.minusDays(1));

        double todayRate = changeBaseAndCalculateRatio(todayRates, code.toUpperCase());
        double yesterdayRate = changeBaseAndCalculateRatio(yesterdayRates, code.toUpperCase());

        return todayRate > yesterdayRate;
    }

    private double changeBaseAndCalculateRatio(ExchangeRate exchangeRate, String code) {
        if (openexchangeConfig.getCurrencyCode().equals(code)) {
            return exchangeRate.getRates().get(openexchangeConfig.getCurrencyCode());
        }
        Map<String, Double> rates = exchangeRate.getRates();
        Double baseRate = rates.get(code);
        if (baseRate == null) {
            throw new InvalidCurrencyException("There is no such currency code: " + code + ", enter valid currency code!");
        }
        Double rubs = rates.get(openexchangeConfig.getCurrencyCode());
        return baseRate / rubs;
    }
}
