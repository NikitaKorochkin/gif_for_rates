package ru.nkorochkin.gif_for_rates.services.exchange.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.nkorochkin.gif_for_rates.clients.exchange.ExchangeClient;
import ru.nkorochkin.gif_for_rates.configuration.OpenexchangeConfig;
import ru.nkorochkin.gif_for_rates.dto.exchange.ExchangeRate;

import java.time.LocalDate;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static ru.nkorochkin.gif_for_rates.util.DateFormattingUtils.LOCAL_DATE_FORMATTER;

@ExtendWith(MockitoExtension.class)
class ExchangeServiceTest {

    @InjectMocks
    private ExchangeServiceImpl mockExchangeService;

    @Mock
    private ExchangeClient exchangeClient;

    @Mock
    private OpenexchangeConfig openexchangeConfig;

    @Test
    void should_return_rate_for_date() {
        String appId = "someAppId";
        when(openexchangeConfig.getAppId()).thenReturn(appId);

        LocalDate now = LocalDate.now();
        ExchangeRate exchangeRateToday = new ExchangeRate();
        exchangeRateToday.setRates(Map.of("RUB", 100.0));
        when(exchangeClient.getRateFromDate(LOCAL_DATE_FORMATTER.format(now), appId)).thenReturn(exchangeRateToday);

        ExchangeRate todayRes = exchangeClient.getRateFromDate(LOCAL_DATE_FORMATTER.format(now), openexchangeConfig.getAppId());

        assertThat(todayRes.getRates(), is(exchangeRateToday.getRates()));
    }

    @Test
    void should_evaluate_bigger_rate() {
        String appId = "someAppId";
        when(openexchangeConfig.getAppId()).thenReturn(appId);
        String currencyCode = "RUB";
        when(openexchangeConfig.getCurrencyCode()).thenReturn(currencyCode);

        ExchangeRate tr = new ExchangeRate();
        tr.setRates(Map.of(currencyCode, 2.0));
        LocalDate now = LocalDate.now();
        when(exchangeClient.getRateFromDate(LOCAL_DATE_FORMATTER.format(now), appId)).thenReturn(tr);

        ExchangeRate yr = new ExchangeRate();
        yr.setRates(Map.of(currencyCode, 1.8));
        LocalDate yesterday = now.minusDays(1);
        when(exchangeClient.getRateFromDate(LOCAL_DATE_FORMATTER.format(yesterday), appId)).thenReturn(yr);

        boolean result = mockExchangeService.isRateBiggerThanYesterday(currencyCode);

        assertTrue(result);
    }

}
