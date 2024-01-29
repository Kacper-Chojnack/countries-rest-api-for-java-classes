package pl.countries.updater.mappers;


import pl.countries.countriesdata.model.Country;
import pl.countries.countriesdata.model.Currency;
import pl.countries.countriesdata.model.Flags;
import pl.countries.countriesdata.model.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.countries.randomcountries.contract.CountryDTO;
import pl.countries.randomcountries.contract.CurrencyDTO;
import pl.countries.randomcountries.contract.FlagsDTO;
import pl.countries.randomcountries.contract.LocationDTO;

@Component
public class Mapper implements IMapper {

    private final CountryMapper countryMapper;
    private final CurrencyMapper currencyMapper;
    private final FlagMapper flagMapper;
    private final LocationMapper locationMapper;

    @Autowired
    public Mapper(CountryMapper countryMapper, CurrencyMapper currencyMapper,
                      FlagMapper flagMapper, LocationMapper locationMapper) {
        this.countryMapper = countryMapper;
        this.currencyMapper = currencyMapper;
        this.flagMapper = flagMapper;
        this.locationMapper = locationMapper;
    }

    @Override
    public IMap<CountryDTO, Country> country() {
        return countryMapper;
    }

    @Override
    public IMap<CurrencyDTO, Currency> currency() {
        return currencyMapper;
    }

    @Override
    public IMap<FlagsDTO, Flags> flags() {
        return flagMapper;
    }

    @Override
    public IMap<LocationDTO, Location> location() {
        return locationMapper;
    }
}
