package pl.countries.randomcountries.contract;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class CountryDTO {
    private NameDTO name; // Nazwa kraju
    private boolean independent; // Czy kraj jest niezależny
    private boolean unMember; // Czy kraj jest członkiem ONZ
    private String region; // Region
    private String subregion; // Podregion
    private boolean landlocked; // Czy kraj jest śródlądowy
    private double area; // Powierzchnia kraju
    private int population; // Populacja
    private Map<String, CurrencyDTO> currencies = new HashMap<>();
    private List<String> borders; // Granice (kody krajów)
    private List<String> capital; // Stolica
    private FlagsDTO flags; // Flagi
}
