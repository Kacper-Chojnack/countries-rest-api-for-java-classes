package pl.countries.randomcountries.contract;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FlagsDTO {
    private String png; // URL do obrazka flagi w formacie PNG
    private String alt; // Opis alternatywny flagi
}

