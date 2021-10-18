package ru.nkorochkin.gif_for_rates.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "giphy")
public class GiphyConfig {
    private String apiKey;
    private String apiUrl;
    private String endpoint;
    private String tagRich;
    private String tagBroke;
    private String rating;
}
