package pl.countries.countriesdata.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Flags {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String png;
    private String alt;

    @OneToOne
    private Country country;
}

