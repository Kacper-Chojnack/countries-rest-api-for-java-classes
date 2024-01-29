package pl.countries.randomcountries.contract;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CountryResultsDto {
    private List<CountryDTO> countries; // Lista krajów
}

