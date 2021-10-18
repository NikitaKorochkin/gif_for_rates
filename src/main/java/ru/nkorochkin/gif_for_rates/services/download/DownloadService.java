package ru.nkorochkin.gif_for_rates.services.download;

import org.springframework.http.ResponseEntity;

import java.net.URI;

public interface DownloadService {
    ResponseEntity<byte[]> downloadGif(URI uri);
}
