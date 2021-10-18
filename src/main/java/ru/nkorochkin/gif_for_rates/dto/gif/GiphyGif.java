package ru.nkorochkin.gif_for_rates.dto.gif;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GiphyGif {

    private GifData data;

    @Data
    public static class GifData {
        @JsonProperty("image_original_url")
        private String imageOriginalUrl;
    }

}
