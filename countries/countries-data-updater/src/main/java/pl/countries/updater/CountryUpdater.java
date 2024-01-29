package pl.countries.updater;

import lombok.extern.slf4j.Slf4j;
import pl.countries.countriesdata.model.Location;
import pl.countries.countriesdata.repositories.IRepositoriesCatalog;
import org.springframework.stereotype.Service;
import pl.countries.countriesdata.repositories.LocationRepository;
import pl.countries.randomcountries.apiclient.CountryApiClient;
import pl.countries.randomcountries.contract.CountryDTO;
import pl.countries.updater.mappers.IMapper;
import pl.countries.updater.mappers.IUpdate;

import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
public class CountryUpdater implements IUpdate {

    private final IMapper map;
    private final CountryApiClient client;
    private final IRepositoriesCatalog database;
    private final LocationRepository locationRepository;

    public CountryUpdater(CountryApiClient client, IMapper map, IRepositoriesCatalog database, LocationRepository locationRepository) {
        this.client = client;
        this.map = map;
        this.database = database;
        this.locationRepository = locationRepository;
    }

    @Override
    public void update() {
        log.info("Updating countries data from API");
        try {
            client.getAllCountries().forEach(this::saveCountry);
        } catch (Exception e) {
            log.error("Error while updating countries data", e);
        }
    }

    private void saveCountry(CountryDTO countryDto) {
        log.debug("Saving country: {}", countryDto.getName().getCommon());
        var country = map.country().toEntity(countryDto);
        database.getCountries().save(country);

        if (countryDto.getCapital() != null && !countryDto.getCapital().isEmpty()) {
            List<Location> capitals = new ArrayList<>();
            for (String capitalName : countryDto.getCapital()) {
                var existingCapital = locationRepository.findByName(capitalName);
                Location capital;
                if (existingCapital != null) {
                    capital = existingCapital;
                } else {
                    capital = new Location();
                    capital.setName(capitalName);
                }
                capital.setCountry(country);
                capital.setCapital(true);
                capitals.add(capital);
                database.getLocations().save(capital);
            }
            country.setCapitals(capitals);
        }

        if (countryDto.getCurrencies() != null) {
            countryDto.getCurrencies().forEach((code, currencyDto) -> {
                var currency = map.currency().toEntity(currencyDto);
                currency.setCountry(country);
                database.getCurrencies().save(currency);
            });
        }

        if (countryDto.getFlags() != null) {
            var flags = map.flags().toEntity(countryDto.getFlags());
            flags.setCountry(country);
            database.getFlags().save(flags);
        }
    }
}

