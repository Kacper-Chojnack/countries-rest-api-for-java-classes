package pl.countries.countriesdata.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    private boolean isCapital;

    @ManyToOne
    private Country country;

}

