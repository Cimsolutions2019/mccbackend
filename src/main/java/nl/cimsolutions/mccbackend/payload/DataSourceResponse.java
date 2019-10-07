package nl.cimsolutions.mccbackend.payload;

import lombok.Getter;
import lombok.Setter;
import nl.cimsolutions.mccbackend.model.DataSource;
import nl.cimsolutions.mccbackend.model.WeatherStation;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class DataSourceResponse {

    private Long id;
    private String name;
    private String link;
    private List<WeatherStation> weatherStations;

    public DataSourceResponse(DataSource dataSource) {
        this.id = dataSource.getId();
        this.name = dataSource.getName();
        this.link = dataSource.getLink();
        this.weatherStations = dataSource.getWeatherStations();
    }
}
