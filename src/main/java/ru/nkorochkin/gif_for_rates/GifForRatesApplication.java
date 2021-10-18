package ru.nkorochkin.gif_for_rates;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import ru.nkorochkin.gif_for_rates.configuration.GiphyConfig;
import ru.nkorochkin.gif_for_rates.configuration.OpenexchangeConfig;

@SpringBootApplication
@EnableFeignClients
@EnableConfigurationProperties(value = {
        GiphyConfig.class,
        OpenexchangeConfig.class
})
public class GifForRatesApplication {

    public static void main(String[] args) {
        SpringApplication.run(GifForRatesApplication.class, args);
    }

}
