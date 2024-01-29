package pl.countries.countriesdata.repositories;

import pl.countries.countriesdata.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
    List<Country> findAllByOrderByPopulationDesc();

    List<Country> findAllByOrderByCommonAsc();
}
