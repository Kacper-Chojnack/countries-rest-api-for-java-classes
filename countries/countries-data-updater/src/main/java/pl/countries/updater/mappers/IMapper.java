package pl.countries.updater.mappers;


import pl.countries.countriesdata.model.Country;
import pl.countries.countriesdata.model.Currency;
import pl.countries.countriesdata.model.Flags;
import pl.countries.countriesdata.model.Location;
import pl.countries.randomcountries.contract.CountryDTO;
import pl.countries.randomcountries.contract.CurrencyDTO;
import pl.countries.randomcountries.contract.FlagsDTO;
import pl.countries.randomcountries.contract.LocationDTO;


public interface IMapper {
    IMap<CountryDTO, Country> country();
    IMap<CurrencyDTO, Currency> currency();
    IMap<FlagsDTO, Flags> flags();
    IMap<LocationDTO, Location> location();
}

