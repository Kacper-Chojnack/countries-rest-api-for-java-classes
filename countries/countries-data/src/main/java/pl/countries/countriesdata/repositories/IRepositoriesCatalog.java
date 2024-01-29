package pl.countries.countriesdata.repositories;

public interface IRepositoriesCatalog {
    CountryRepository getCountries();

    CurrencyRepository getCurrencies();

    FlagsRepository getFlags();
    LocationRepository getLocations();

}
