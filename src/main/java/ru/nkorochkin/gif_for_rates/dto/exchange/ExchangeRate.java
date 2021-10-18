package ru.nkorochkin.gif_for_rates.dto.exchange;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeRate {
    private String disclaimer;
    private String license;
    private Instant timestamp;
    private String base;
    private Map<String, Double> rates;
}
