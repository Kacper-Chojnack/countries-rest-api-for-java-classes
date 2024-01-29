package pl.countries.countriesdata.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "country_seq")
    @SequenceGenerator(name = "country_seq", sequenceName = "country_seq", allocationSize = 1)
    private long id;
    private String common;
    private String official;
    private boolean independent;
    private boolean unMember;
    private String region;
    private String subregion;
    private boolean landlocked;
    private double area;
    private int population;

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL)
    private List<Currency> currencies;

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL)
    private List<Location> borders;


    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL)
    private List<Location> capitals;

    @OneToOne(mappedBy = "country", cascade = CascadeType.ALL)
    private Flags flags;

}
