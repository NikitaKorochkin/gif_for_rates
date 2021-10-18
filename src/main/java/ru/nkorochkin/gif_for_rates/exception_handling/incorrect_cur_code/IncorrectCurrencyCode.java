package ru.nkorochkin.gif_for_rates.exception_handling.incorrect_cur_code;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IncorrectCurrencyCode {
    private String info;
}
