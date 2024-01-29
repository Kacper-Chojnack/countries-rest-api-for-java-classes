package pl.countries.webapi.contract;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CountryDTO {
    private Long id;
    private String common;
    private String official;
    private boolean independent;
    private boolean unMember;
    private String region;
    private String subregion;
    private boolean landlocked;
    private double area;
    private int population;
    private List<CurrencyDTO> currencies;
    private List<LocationDTO> borders;
    private List<LocationDTO> capitals;
    private FlagsDTO flags;

}
