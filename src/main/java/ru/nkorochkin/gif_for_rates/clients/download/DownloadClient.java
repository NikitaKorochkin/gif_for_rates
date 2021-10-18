package ru.nkorochkin.gif_for_rates.clients.download;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.net.URI;

@FeignClient(value = "download", url = "https://placeholder")
public interface DownloadClient {
    @GetMapping
    ResponseEntity<byte[]> downloadGif(URI gifUrl);
}
