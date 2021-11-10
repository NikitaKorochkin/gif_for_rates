package ru.nkorochkin.gif_for_rates.dto.gif;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class GiphyGif {
    @JsonProperty("data")
    private Datas data;

    @Data
    public static class Datas {
        @JsonProperty("images")
        private Images images;

        @Data
        public static class Images   {
            @JsonProperty("original")
            private Original original;

            @Data
            public static class Original    {
                @JsonProperty("url")
                private String url;
            }
        }
    }
}
