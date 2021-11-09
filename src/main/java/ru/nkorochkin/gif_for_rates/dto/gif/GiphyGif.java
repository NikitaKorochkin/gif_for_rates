package ru.nkorochkin.gif_for_rates.dto.gif;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GiphyGif {

    private GifData data;

    @Data
    public static class GifData {
        @JsonProperty("images")
       private Map<String, String> images;
       private OriginalImage originalImage;

       @Data
       public static class OriginalImage    {
           @JsonProperty("original")
           private Map<String, String> original;
           public String getImageOriginalUrl()  {
               return original.get("url");
           }
       }
    }

}
