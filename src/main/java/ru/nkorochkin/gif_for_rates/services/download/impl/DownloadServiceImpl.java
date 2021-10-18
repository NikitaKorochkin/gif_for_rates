package ru.nkorochkin.gif_for_rates.services.download.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.nkorochkin.gif_for_rates.clients.download.DownloadClient;
import ru.nkorochkin.gif_for_rates.services.download.DownloadService;

import java.net.URI;

@Service
@RequiredArgsConstructor
public class DownloadServiceImpl implements DownloadService {

   private final DownloadClient downloadClient;

    @Override
    public ResponseEntity<byte[]> downloadGif(URI uri) {
        return downloadClient.downloadGif(uri);
    }
}
