package pl.countries.countriesdata.repositories;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@Getter
@RequiredArgsConstructor
public class RepositoriesCatalog implements IRepositoriesCatalog {
    private final CountryRepository countries;
    private final CurrencyRepository currencies;
    private final LocationRepository locations;
    private final FlagsRepository flags;
}
