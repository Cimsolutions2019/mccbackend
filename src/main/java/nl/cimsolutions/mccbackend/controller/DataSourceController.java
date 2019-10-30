package nl.cimsolutions.mccbackend.controller;

import nl.cimsolutions.mccbackend.model.DataSource;
import nl.cimsolutions.mccbackend.model.WeatherStation;
import nl.cimsolutions.mccbackend.model.types.SensorIntervals;
import nl.cimsolutions.mccbackend.payload.DataSourceMeasurementResponse;
import nl.cimsolutions.mccbackend.payload.DataSourceResponse;
import nl.cimsolutions.mccbackend.payload.WeatherStationResponse;
import nl.cimsolutions.mccbackend.repository.DataSourceRepository;
import nl.cimsolutions.mccbackend.repository.DataSourceMartRepository;
import nl.cimsolutions.mccbackend.repository.WeatherStationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/datasource")
@CrossOrigin
public class DataSourceController {

    @Autowired
    DataSourceRepository dataSourceRepository;

    @Autowired
    WeatherStationRepository weatherStationRepository;

    @Autowired
    DataSourceMartRepository dataSourceMartRepository;

    @GetMapping
    public List<DataSource> getDataSource() {
        return dataSourceRepository.findAll();
    }

    @GetMapping("/{dataSourceId}/measurements")
    public List<DataSourceMeasurementResponse> getDataSourceMeasurements( @RequestParam("date") @DateTimeFormat(pattern="yyyy-MM-dd") Date date,
                                                                          @RequestParam("enddate") @DateTimeFormat(pattern="yyyy-MM-dd") Date date2,
                                                                          @PathVariable Long dataSourceId,
                                                                          @RequestParam("weatherStationNumber") String weatherStationNumber,
                                                                          @RequestParam("sensor") String sensor,
                                                                          @RequestParam("interval") SensorIntervals sensorInterval) {
        switch (sensorInterval) {
            case DAY:
                return dataSourceMartRepository.findavgDataPerDay(date, date2, sensor.toLowerCase(),  dataSourceId);
            default:
                return dataSourceMartRepository.findavgDataPerHour(date, date2, sensor.toLowerCase(),  dataSourceId);
        }
    }

    @GetMapping("/{dataSourceId}/weatherstations")
    public Optional<DataSourceResponse> getResearch(@PathVariable Long dataSourceId) {
        return dataSourceRepository.findById(dataSourceId).map(DataSourceResponse::new);
    }

    @GetMapping("/{dataSourceId}/weatherstations/distance")
    public List<WeatherStationResponse> getWeatherStationsAndDistance(@PathVariable Long dataSourceId, @RequestParam double lon, @RequestParam double lat) {
        return weatherStationRepository.findWeatherStationsByDataSource(dataSourceId).stream()
                .map((WeatherStation weatherStation) -> new WeatherStationResponse(weatherStation, lon, lat))
                .collect(Collectors.toList());
    }

    @PostMapping
    public DataSource createDataSource(@Valid @RequestBody DataSource dataSource) {
        return dataSourceRepository.save(dataSource);
    }
}
