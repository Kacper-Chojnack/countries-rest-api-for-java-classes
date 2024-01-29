package pl.countries.updater.mappers;

import pl.countries.randomcountries.contract.CurrencyDTO;
import pl.countries.countriesdata.model.Currency;
import org.springframework.stereotype.Component;

@Component
public class CurrencyMapper implements IMap<CurrencyDTO, Currency> {

    @Override
    public Currency toEntity(CurrencyDTO currencyDto) {
        var currency = new Currency();
        currency.setName(currencyDto.getName());
        currency.setSymbol(currencyDto.getSymbol());
        return currency;
    }
}

