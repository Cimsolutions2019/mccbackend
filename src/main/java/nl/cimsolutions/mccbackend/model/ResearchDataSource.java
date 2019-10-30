package nl.cimsolutions.mccbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class ResearchDataSource implements Serializable {

    @JsonIgnore
    @Id
    @ManyToOne
    @JoinColumn
    private Research research;

    @Id
    @ManyToOne
    @JoinColumn
    private DataSource dataSource;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="research_weatherstations")
    @JoinColumn(name="weatherstations_id")
    private Set<WeatherStation> weatherStations = new HashSet<>();

    public ResearchDataSource(Research research, DataSource dataSource) {
        this.research = research;
        this.dataSource = dataSource;
    }


}
