package nl.cimsolutions.mccbackend.controller;

import nl.cimsolutions.mccbackend.exception.ResourceNotFoundException;
import nl.cimsolutions.mccbackend.model.Location;
import nl.cimsolutions.mccbackend.model.Research;
import nl.cimsolutions.mccbackend.model.Voyager;
import nl.cimsolutions.mccbackend.payload.MeasurementResponse;
import nl.cimsolutions.mccbackend.payload.request.ResearchRequest;
import nl.cimsolutions.mccbackend.repository.LocationRepository;
import nl.cimsolutions.mccbackend.repository.ResearchRepository;
import nl.cimsolutions.mccbackend.repository.VoyagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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

    @GetMapping("")
    public List<Research> getResearches() {
        return researchRepository.findAll();
    }

    @GetMapping("/{researchId}/voyagers}")
    public Set<Voyager> getVoyager(@PathVariable Long researchId) {
        Optional<Research> research = researchRepository.findById(researchId);
        return research.get().getVoyagers();
    }

    @PostMapping("")
    public Research createResearch(@Valid @RequestBody ResearchRequest researchRequest) {
        Research research = new Research(researchRequest.getName(), researchRequest.getResearchArea(), researchRequest.getDescription());
        for (int id: researchRequest.getVoyagerIds()) {
            Optional<Voyager> voyager = voyagerRepository.findById(Long.valueOf(id));
            research.getVoyagers().add(voyager.get());
        }
        return researchRepository.save(research);
    }

    @PostMapping("/{researchId}/voyager/{voyagerId}")
    public Location postResearchVoyagerMeasurements(@PathVariable Long researchId, @PathVariable Long voyagerId, @Valid @RequestBody Location location ) {
        Optional<Voyager> voyager = voyagerRepository.findById(Long.valueOf(voyagerId));
        Optional<Research> research = researchRepository.findById(Long.valueOf(researchId));
        Location savedLocation = locationRepository.save(location);
        voyager.get().addLocation(savedLocation);
        research.get().getLocations().add(savedLocation);
        voyagerRepository.save(voyager.get());
        researchRepository.save(research.get());
        return savedLocation;
    }
}
