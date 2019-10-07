package nl.cimsolutions.mccbackend.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "data_sources")
public class DataSource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String link;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "data_source_id")
    private List<Location> locations = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "dataSource", cascade = CascadeType.ALL)
    private Set<ResearchDataSource> researches = new HashSet<>();

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "data_source_id")
    private List<WeatherStation> weatherStations = new ArrayList<>();

    public DataSource(String name, String link) {
        this.name = name;
        this.link = link;
    }

}
