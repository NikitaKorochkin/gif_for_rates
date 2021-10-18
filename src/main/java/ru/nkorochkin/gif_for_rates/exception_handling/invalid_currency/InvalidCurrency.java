package ru.nkorochkin.gif_for_rates.exception_handling.invalid_currency;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvalidCurrency {
    String info;
}
