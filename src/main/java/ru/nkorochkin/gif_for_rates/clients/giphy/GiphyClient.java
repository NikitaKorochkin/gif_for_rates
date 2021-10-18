package ru.nkorochkin.gif_for_rates.clients.giphy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.nkorochkin.gif_for_rates.clients.config.ClientConfiguration;
import ru.nkorochkin.gif_for_rates.configuration.GiphyConfig;
import ru.nkorochkin.gif_for_rates.dto.gif.GiphyGif;

@FeignClient(value = "giphy", url = "${giphy.apiUrl}",
        configuration = {ClientConfiguration.class, GiphyConfig.class})
public interface GiphyClient {
    @RequestMapping(method = RequestMethod.GET, value = "${giphy.endpoint}")
    GiphyGif getRandomGifByTag(
            @RequestParam("api_key") String apiKey,
            @RequestParam("tag") String tag,
            @RequestParam("rating") String rating
    );
}
