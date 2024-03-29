package nl.cimsolutions.mccbackend.controller;

import nl.cimsolutions.mccbackend.exception.BadRequestException;
import nl.cimsolutions.mccbackend.exception.ResourceNotFoundException;
import nl.cimsolutions.mccbackend.model.*;
import nl.cimsolutions.mccbackend.model.types.ResearchTypes;
import nl.cimsolutions.mccbackend.model.types.SensorIntervals;
import nl.cimsolutions.mccbackend.model.types.VoyagerSensors;
import nl.cimsolutions.mccbackend.payload.MeasurementResponse;
import nl.cimsolutions.mccbackend.payload.ResearchResponse;
import nl.cimsolutions.mccbackend.payload.VoyagerTempResponse;
import nl.cimsolutions.mccbackend.payload.request.ResearchDataSourceRequest;
import nl.cimsolutions.mccbackend.payload.request.ResearchRequest;
import nl.cimsolutions.mccbackend.payload.request.SensorIntervalRequest;
import nl.cimsolutions.mccbackend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/research")
@CrossOrigin
public class ResearchController {

    @Autowired
    ResearchRepository researchRepository;

    @Autowired
    VoyagerRepository voyagerRepository;

    @Autowired
    LocationRepository locationRepository;
    
    @Autowired
    DataSourceRepository dataSourceRepository;

    @Autowired
    WeatherStationRepository weatherStationRepository;


    @GetMapping("")
    public List<ResearchResponse> getResearches() {
        return researchRepository.findAll().stream().map(ResearchResponse::new).collect(Collectors.toList());
    }

    @GetMapping("/{researchId}")
    public Optional<Research> getResearch(@PathVariable Long researchId) {
        return researchRepository.findById(researchId);
    }
    
    @GetMapping("/{researchId}/datasources")
    public Set<ResearchDataSource> getDataSources(@PathVariable Long researchId) {
        Optional<Research> research = researchRepository.findById(researchId);
        return research.get().getDataSources();
    }
    
    @GetMapping("/{researchId}/voyagers")
    public Set<Voyager> getResearchVoyagers(@PathVariable Long researchId) {
        Optional<Research> research = researchRepository.findById(researchId);
        return research.get().getVoyagers();
    }

    @GetMapping("/intervals")
    public SensorIntervals[] getAllSensorIntervals() {
        SensorIntervals[] allSensorIntervals = SensorIntervals.values();
        return allSensorIntervals;
    }

    @GetMapping("/types")
    public ResearchTypes[] getAllResearchTypes() {
        ResearchTypes[] allSensorIntervals = ResearchTypes.values();
        return allSensorIntervals;
    }

    @GetMapping("/{researchId}/voyager/{voyagerId}/measurements")
    public List<Location> getResearchVoyagerMeasurements(@PathVariable Long researchId, @PathVariable Long voyagerId,
                                                         @RequestParam("date") @DateTimeFormat(pattern="yyyy-MM-dd") Date date,
                                                         @RequestParam("date2") @DateTimeFormat(pattern="yyyy-MM-dd") Date date2) {

        return locationRepository.findByResearchIdAndVoyagerId(researchId, voyagerId, date, date2);
    }

    @GetMapping("/{researchId}/voyager/{voyagerId}/measurements/temperature")
    public List<VoyagerTempResponse> getResearchVoyagerTemperatureMeasurements(@PathVariable Long researchId, @PathVariable Long voyagerId,
                                                                               @RequestParam("date") @DateTimeFormat(pattern="yyyy-MM-dd") Date date,
                                                                               @RequestParam("date2") @DateTimeFormat(pattern="yyyy-MM-dd") Date date2,
                                                                               @RequestParam("interval") SensorIntervals sensorInterval) {
        switch (sensorInterval) {
            case MINUTELY:
                return locationRepository.tempPerMinutePerDay(voyagerId, researchId, date, date2);
            case DAY:
                return locationRepository.avgTempPerDay(voyagerId, researchId, date, date2);
            default:
                return locationRepository.avgTempPerHourPerDay(voyagerId, researchId, date, date2);
        }
    }

    @GetMapping("/{researchId}/voyager/{voyagerId}/measurements/humidity")
    public List<VoyagerTempResponse> getResearchVoyagerHumidityMesaurements(@PathVariable Long researchId, @PathVariable Long voyagerId,
                                                                            @RequestParam("date") @DateTimeFormat(pattern="yyyy-MM-dd") Date date,
                                                                            @RequestParam("date2") @DateTimeFormat(pattern="yyyy-MM-dd") Date date2,
                                                                            @RequestParam("interval") SensorIntervals sensorInterval) {
        switch (sensorInterval) {
            case MINUTELY:
                return locationRepository.humidityPerMinutePerDay(voyagerId, researchId, date, date2);
            case DAY:
                return locationRepository.avgHumidityPerDay(voyagerId, researchId, date, date2);
            default:
                return locationRepository.avgHumidityPerHourPerDay(voyagerId, researchId, date, date2);
        }
    }


    @GetMapping("/{researchId}/datasource/{dataSourceId}/measurements")
    public List<Location> getResearchDataSourceMeasurements(@PathVariable Long researchId, @PathVariable Long dataSourceId) {
        return locationRepository.findByResearchIdAndDataSourceId(researchId, dataSourceId);
    }

    @DeleteMapping("/{researchId}")
    public ResponseEntity<?> deleteResearch(@PathVariable Long researchId) {
        return researchRepository.findById(researchId)
                .map(research -> {
                    research.getVoyagers().stream().forEach(voy -> {
                        voy.setInResearch(false);

                            });
                	researchRepository.delete(research);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Research not found with id " + researchId));
    }
    
    @PutMapping("/{researchId}")
    public Research updateResearch(@PathVariable Long researchId,
                                   @Valid @RequestBody ResearchRequest research) {
        return researchRepository.findById(researchId)
                .map(res -> {
                        res.setName(research.getName());
                        res.setResearchArea(research.getResearchArea());
                        res.setDescription(research.getDescription());
                        res.setEndDate(research.getEndDate());
                        return researchRepository.save(res);
                   }).orElseThrow(() -> new ResourceNotFoundException("Research not found with id " + researchId));
    }

    @PostMapping("")
    public Research createResearch(@Valid @RequestBody ResearchRequest researchRequest) {
        Research research = new Research(researchRequest.getName(), researchRequest.getResearchArea(), researchRequest.getDescription(),
                researchRequest.getStartDate(), researchRequest.getEndDate(), researchRequest.getOwner(), researchRequest.getResearchType());
        for (int id: researchRequest.getVoyagerIds()) {
            Optional<Voyager> voyager = voyagerRepository.findById(Long.valueOf(id));
            if (!voyager.get().getInResearch()) {
                research.getVoyagers().add(voyager.get());
                voyager.get().setInResearch(true);
            } else {
                throw new BadRequestException("Voyager is already assigned to an research");
            }
        }

        for (ResearchDataSourceRequest dataSourceRequest: researchRequest.getResearchDataSourceRequest()) {
            Optional<DataSource> dataSource = dataSourceRepository.findById(Long.valueOf(dataSourceRequest.getDataSourceId()));
            ResearchDataSource researchDataSource = new ResearchDataSource(research, dataSource.get());
            for (int id: dataSourceRequest.getWeatherStationsId()) {
                Optional<WeatherStation> weatherStation = weatherStationRepository.findById(Long.valueOf(id));
                researchDataSource.getWeatherStations().add(weatherStation.get());
            }
            research.getDataSources().add(researchDataSource);
        }

        for (SensorIntervalRequest sensorIntervalRequest: researchRequest.getSensorIntervals()) {
            SensorInterval sensorInterval = new SensorInterval(sensorIntervalRequest.getVoyagerSensor(), sensorIntervalRequest.getSensorInterval());
            research.getSensorIntervals().add(sensorInterval);
        }

        return researchRepository.save(research);
    }
    
    @PostMapping("/{researchId}/voyager/{voyagerId}")
    public Location postResearchVoyagerMeasurements(@PathVariable Long researchId, @PathVariable Long voyagerId, @Valid @RequestBody Location location ) {
        Optional<Voyager> voyager = voyagerRepository.findById(Long.valueOf(voyagerId));
        Optional<Research> research = researchRepository.findById(Long.valueOf(researchId));
        Location savedLocation = locationRepository.save(location);
        voyager.get().getLocations().add(savedLocation);
        research.get().getLocations().add(savedLocation);
        voyagerRepository.save(voyager.get());
        researchRepository.save(research.get());
        return savedLocation;
    }
}
