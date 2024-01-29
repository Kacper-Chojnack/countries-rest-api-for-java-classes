package pl.countries.webapi.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.countries.webapi.contract.CountryDTO;
import pl.countries.webapi.contract.CurrencyDTO;
import pl.countries.webapi.contract.FlagsDTO;
import pl.countries.webapi.contract.LocationDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import pl.countries.countriesdata.model.Country;
import pl.countries.countriesdata.model.Currency;
import pl.countries.countriesdata.model.Flags;
import pl.countries.countriesdata.model.Location;
import pl.countries.countriesdata.repositories.CountryRepository;
import pl.countries.countriesdata.repositories.CurrencyRepository;
import pl.countries.countriesdata.repositories.FlagsRepository;
import pl.countries.countriesdata.repositories.LocationRepository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@Slf4j
public class CountryController {

    private final CountryRepository countryRepository;
    private final CurrencyRepository currencyRepository;
    private final LocationRepository locationRepository;
    private final FlagsRepository flagsRepository;


    @GetMapping("/currencies")
    public ResponseEntity<List<CurrencyDTO>> getAllCurrencies() {
        List<Currency> currencies = currencyRepository.findAll();
        List<CurrencyDTO> currencyDTOs = currencies.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
        log.info(currencies.size() + " currencies downloaded.");
        return ResponseEntity.ok(currencyDTOs);
    }

    @GetMapping("/borders")
    public ResponseEntity<List<LocationDTO>> getAllBorders() {
        List<Location> borders = locationRepository.findAll();
        List<LocationDTO> locationDTOs = borders.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
        log.info(borders.size() + " borders downloaded.");
        return ResponseEntity.ok(locationDTOs);
    }

    @GetMapping("/capital")
    public ResponseEntity<LocationDTO> getCapital() {
        Location capital = locationRepository.findCapital();
        LocationDTO locationDTO = mapToDTO(capital);
        log.info("Capital city downloaded.");
        return ResponseEntity.ok(locationDTO);
    }

    @GetMapping("/flags")
    public ResponseEntity<List<FlagsDTO>> getFlags() {
        List<Flags> flagsList = flagsRepository.findAll();
        List<FlagsDTO> flagsDTOList = flagsList.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
        log.info("Flags downloaded.");
        return ResponseEntity.ok(flagsDTOList);
    }

    @GetMapping("/")
    public String index(Model model) {
        List<CountryDTO> countries = countryRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
        model.addAttribute("countries", countries);
        log.info("Homepage.");
        return "index";
    }


    @GetMapping("/countries")
    public String getAllCountries(@RequestParam(required = false) String sort, Model model) {
        log.info("Request to get all countries with sort: {}", sort);
        List<Country> countries;
        if ("population".equals(sort)) {
            log.debug("Sorting countries by population");
            countries = countryRepository.findAllByOrderByPopulationDesc();
        } else if ("alphabetical".equals(sort)) {
            log.debug("Sorting countries alphabetically");
            countries = countryRepository.findAllByOrderByCommonAsc();
        } else {
            log.debug("Getting countries without specific sorting");
            countries = countryRepository.findAll();
        }
        List<CountryDTO> countryDTOs = countries.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
        log.info("Number of countries retrieved: {}", countryDTOs.size());
        model.addAttribute("countries", countryDTOs);
        return "index";
    }

    @GetMapping("/country")
    public String countryDetails(@RequestParam("details") Long countryId, Model model) {
        log.info("Request to get details for country id: {}", countryId);
        CountryDTO country = mapToDTO(Objects.requireNonNull(countryRepository.findById(countryId).orElse(null)));
        if (country == null) {
            log.warn("Country not found for id: {}", countryId);
            return "redirect:/countries";
        }
        model.addAttribute("country", country);
        return "countryDetails";
    }

    @PostMapping("/deleteCountry")
    public String deleteCountry(@RequestParam("id") Long countryId) {
        log.info("Request to delete country with id: {}", countryId);
        countryRepository.deleteById(countryId);
        log.info("Country deleted with id: {}", countryId);
        log.info("Back to homepage.");
        return "redirect:/countries";
    }


    private CurrencyDTO mapToDTO(Currency currency) {
        CurrencyDTO dto = new CurrencyDTO();
        dto.setId(currency.getId());
        dto.setName(currency.getName());
        dto.setSymbol(currency.getSymbol());
        return dto;
    }

    private LocationDTO mapToDTO(Location location) {
        LocationDTO dto = new LocationDTO();
        dto.setId(location.getId());
        dto.setName(location.getName());
        return dto;
    }

    private FlagsDTO mapToDTO(Flags flags) {
        FlagsDTO dto = new FlagsDTO();
        dto.setId(flags.getId());
        dto.setPng(flags.getPng());
        dto.setAlt(flags.getAlt());
        return dto;
    }

    private CountryDTO mapToDTO(Country country) {
        CountryDTO dto = new CountryDTO();
        dto.setId(country.getId());
        dto.setCommon(country.getCommon());
        dto.setOfficial(country.getOfficial());
        dto.setIndependent(country.isIndependent());
        dto.setUnMember(country.isUnMember());
        dto.setRegion(country.getRegion());
        dto.setSubregion(country.getSubregion());
        dto.setLandlocked(country.isLandlocked());
        dto.setArea(country.getArea());
        dto.setPopulation(country.getPopulation());

        if (country.getCapitals() != null) {
            dto.setCapitals(country.getCapitals().stream()
                    .map(capital -> {
                        LocationDTO locationDTO = new LocationDTO();
                        locationDTO.setId(capital.getId());
                        locationDTO.setName(capital.getName());
                        return locationDTO;
                    })
                    .collect(Collectors.toList()));
        }

        if (country.getCurrencies() != null) {
            dto.setCurrencies(country.getCurrencies().stream()
                    .map(currency -> {
                        CurrencyDTO currencyDTO = new CurrencyDTO();
                        currencyDTO.setId(currency.getId());
                        currencyDTO.setName(currency.getName());
                        currencyDTO.setSymbol(currency.getSymbol());
                        return currencyDTO;
                    })
                    .collect(Collectors.toList()));
        }

        if (country.getBorders() != null) {
            dto.setBorders(country.getBorders().stream()
                    .map(border -> {
                        LocationDTO locationDTO = new LocationDTO();
                        locationDTO.setId(border.getId());
                        locationDTO.setName(border.getName());
                        return locationDTO;
                    })
                    .collect(Collectors.toList()));
        }

        if (country.getFlags() != null) {
            FlagsDTO flagsDTO = new FlagsDTO();
            flagsDTO.setId(country.getFlags().getId());
            flagsDTO.setPng(country.getFlags().getPng());
            flagsDTO.setAlt(country.getFlags().getAlt());
            dto.setFlags(flagsDTO);
        }

        return dto;
    }


}
