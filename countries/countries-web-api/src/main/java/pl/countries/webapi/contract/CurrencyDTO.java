package pl.countries.webapi.contract;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CurrencyDTO {
    private Long id;
    private String name;
    private String symbol;

}
