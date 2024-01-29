package pl.countries.updater.mappers;

import pl.countries.countriesdata.model.Flags;
import org.springframework.stereotype.Component;
import pl.countries.randomcountries.contract.FlagsDTO;

@Component
public class FlagMapper implements IMap<FlagsDTO, Flags> {

    @Override
    public Flags toEntity(FlagsDTO flagsDTO) {
        var flag = new Flags();
        flag.setPng(flagsDTO.getPng());
        flag.setAlt(flagsDTO.getAlt());
        return flag;    }
}

