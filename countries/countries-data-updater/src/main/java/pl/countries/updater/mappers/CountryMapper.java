package pl.countries.updater.mappers;

import pl.countries.randomcountries.contract.CountryDTO;
import pl.countries.countriesdata.model.Country;
import org.springframework.stereotype.Component;

@Component
public class CountryMapper implements IMap<CountryDTO, Country> {

    @Override
    public Country toEntity(CountryDTO countryDto) {
        var country = new Country();
        country.setCommon(countryDto.getName().getCommon());
        country.setOfficial(countryDto.getName().getOfficial());
        country.setIndependent(countryDto.isIndependent());
        country.setUnMember(countryDto.isUnMember());
        country.setRegion(countryDto.getRegion());
        country.setSubregion(countryDto.getSubregion());
        country.setLandlocked(countryDto.isLandlocked());
        country.setArea(countryDto.getArea());
        country.setPopulation(countryDto.getPopulation());
        return country;
    }
}
