package nl.cimsolutions.mccbackend.controller;

import nl.cimsolutions.mccbackend.model.DataSource;
import nl.cimsolutions.mccbackend.model.Research;
import nl.cimsolutions.mccbackend.model.Voyager;
import nl.cimsolutions.mccbackend.model.WeatherStation;
import nl.cimsolutions.mccbackend.payload.DataSourceResponse;
import nl.cimsolutions.mccbackend.payload.ResearchResponse;
import nl.cimsolutions.mccbackend.repository.DataSourceRepository;
import nl.cimsolutions.mccbackend.repository.WeatherStationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/datasource")
@CrossOrigin
public class DataSourceController {

    @Autowired
    DataSourceRepository dataSourceRepository;

    @Autowired
    WeatherStationRepository weatherStationRepository;

    @GetMapping("")
    public List<DataSource> getDataSource(Pageable pageable) {
        return dataSourceRepository.findAll();
    }

    @GetMapping("/{dataSourceId}")
    public Optional<DataSourceResponse> getResearch(@PathVariable Long dataSourceId) {
        return dataSourceRepository.findById(dataSourceId).map(DataSourceResponse::new);
    }

    @PostMapping("/weatherStation")
    public WeatherStation createDataSource(@Valid @RequestBody WeatherStation dataSource) {
        return weatherStationRepository.save(dataSource);
    }

    @PostMapping("")
    public DataSource createDataSource(@Valid @RequestBody DataSource dataSource) {
        return dataSourceRepository.save(dataSource);
    }
}
