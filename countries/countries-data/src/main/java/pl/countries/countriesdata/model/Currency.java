package pl.countries.countriesdata.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String symbol;

    @ManyToOne
    private Country country;
}

