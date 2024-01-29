package pl.countries;

import pl.countries.countriesdata.repositories.IRepositoriesCatalog;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class CountriesDataUpdaterApplication {

    final IRepositoriesCatalog database;

    public CountriesDataUpdaterApplication(IRepositoriesCatalog database) {
        this.database = database;
    }

    public static void main(String[] args) {
        SpringApplication.run(CountriesDataUpdaterApplication.class, args);
    }
}

