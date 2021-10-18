package ru.nkorochkin.gif_for_rates.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "openexchange")
public class OpenexchangeConfig {
    private String appId;
    private String apiUrl;
    private String currencyCode;
    private String exchangeInfoType;
}
