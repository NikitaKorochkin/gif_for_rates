package ru.nkorochkin.gif_for_rates.clients.exchange;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import ru.nkorochkin.gif_for_rates.clients.config.ClientConfiguration;
import ru.nkorochkin.gif_for_rates.dto.exchange.ExchangeRate;


@FeignClient(value = "exchange", url = "${openexchange.apiUrl}",
        configuration = ClientConfiguration.class)
public interface ExchangeClient {
    @RequestMapping(method = RequestMethod.GET, value = "/${openexchange.exchangeInfoType}/{date}.json", params = {"app_id"})
    ExchangeRate getRateFromDate(@PathVariable String date, @RequestParam(value = "app_id") String appId);
}
