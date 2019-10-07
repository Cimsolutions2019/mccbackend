package nl.cimsolutions.mccbackend.controller;

import nl.cimsolutions.mccbackend.exception.BadRequestException;
import nl.cimsolutions.mccbackend.exception.ResourceNotFoundException;
import nl.cimsolutions.mccbackend.model.*;
import nl.cimsolutions.mccbackend.payload.MeasurementResponse;
import nl.cimsolutions.mccbackend.payload.ResearchResponse;
import nl.cimsolutions.mccbackend.payload.request.ResearchDataSourceRequest;
import nl.cimsolutions.mccbackend.payload.request.ResearchRequest;
import nl.cimsolutions.mccbackend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @GetMapping("/{researchId}/voyager/{voyagerId}/measurements")
    public List<Location> getResearchVoyagerMeasurements(@PathVariable Long researchId, @PathVariable Long voyagerId) {
        return locationRepository.findByResearchIdAndVoyagerId(researchId, voyagerId);
    }
    
    @DeleteMapping("/{researchId}")
    public ResponseEntity<?> deleteResearch(@PathVariable Long researchId) {
        return researchRepository.findById(researchId)
                .map(research -> {
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
        Research research = new Research(researchRequest.getName(), researchRequest.getResearchArea(), researchRequest.getDescription(), researchRequest.getStartDate(), researchRequest.getEndDate(),
                researchRequest.getOwner());
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
