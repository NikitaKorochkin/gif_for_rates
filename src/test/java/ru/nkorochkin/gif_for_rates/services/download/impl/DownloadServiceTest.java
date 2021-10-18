package ru.nkorochkin.gif_for_rates.services.download.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import ru.nkorochkin.gif_for_rates.clients.download.DownloadClient;

import java.net.URI;
import java.security.SecureRandom;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DownloadServiceTest {

    @InjectMocks
    private DownloadServiceImpl downloadService;

    @Mock
    private DownloadClient downloadClient;

    @Test
    public void should_return_byte_array()  {
        URI gifUri = URI.create("testuri");
        byte[] responseContent = new byte[100];
        new SecureRandom().nextBytes(responseContent);
        ResponseEntity<byte[]> response = ResponseEntity.ok(responseContent);
        when(downloadClient.downloadGif(gifUri)).thenReturn(response);

        ResponseEntity<byte[]> responseEntity = downloadService.downloadGif(gifUri);

        assertThat(responseEntity, is(response));
    }
}
