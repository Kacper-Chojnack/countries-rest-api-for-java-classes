package pl.countries.countriesdata.repositories;

import pl.countries.countriesdata.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

    @Query("SELECT l FROM Location l WHERE l.isCapital = true")
    Location findCapital();

    Location findByName(String name);
}


