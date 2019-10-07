package nl.cimsolutions.mccbackend.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.cimsolutions.mccbackend.model.DataSource;
import nl.cimsolutions.mccbackend.model.ResearchDataSource;
import nl.cimsolutions.mccbackend.model.WeatherStation;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class ResearchDataSourceResponse {

    private DataSource dataSource;
    private Set<WeatherStation> weatherStations;

    public ResearchDataSourceResponse(ResearchDataSource researchDataSource) {
        this.dataSource = researchDataSource.getDataSource();
        this.weatherStations = researchDataSource.getWeatherStations();
    }
}
