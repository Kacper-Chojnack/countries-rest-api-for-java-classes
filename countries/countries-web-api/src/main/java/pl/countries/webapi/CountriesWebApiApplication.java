package pl.countries.webapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "pl.countries")
@EnableJpaRepositories(basePackages = "pl.countries.countriesdata.repositories")
public class CountriesWebApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CountriesWebApiApplication.class, args);
    }

}
