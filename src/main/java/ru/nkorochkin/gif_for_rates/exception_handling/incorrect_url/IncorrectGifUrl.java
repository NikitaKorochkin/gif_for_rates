package ru.nkorochkin.gif_for_rates.exception_handling.incorrect_url;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IncorrectGifUrl {
    private String info;
}
