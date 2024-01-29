package pl.countries.countriesdata.repositories;

import pl.countries.countriesdata.model.Country;
import pl.countries.countriesdata.model.Flags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlagsRepository extends JpaRepository<Flags, Long> {
    List<Flags> findByCountry(Country country);
    List<Flags> findAll();


}
