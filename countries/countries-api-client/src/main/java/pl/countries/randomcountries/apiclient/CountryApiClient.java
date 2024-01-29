package pl.countries.randomcountries.apiclient;

import lombok.extern.slf4j.Slf4j;
import pl.countries.randomcountries.contract.CountryDTO;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Component
public class CountryApiClient {

    private final RestTemplate restTemplate;
    private final String URL = "https://restcountries.com/v3.1/all";

    public CountryApiClient() {
        this.restTemplate = new RestTemplate();
    }

    public List<CountryDTO> getAllCountries() {
        log.info("Fetching all countries from {}", URL);
        try {
            ResponseEntity<List<CountryDTO>> response = restTemplate.exchange(
                    URL,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<CountryDTO>>() {});
            return response.getBody();
        } catch (Exception e) {
            log.error("Error occurred while fetching countries from {}", URL, e);
            throw e;
        }
    }

}