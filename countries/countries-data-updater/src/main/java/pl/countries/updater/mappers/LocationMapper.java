package pl.countries.updater.mappers;

import pl.countries.countriesdata.model.Location;
import org.springframework.stereotype.Component;
import pl.countries.randomcountries.contract.LocationDTO;

@Component
public class LocationMapper implements IMap<LocationDTO, Location> {

    @Override
    public Location toEntity(LocationDTO locationDTO) {
        var location = new Location();
        location.setName(locationDTO.getName());
        return location;
    }
}