package nl.cimsolutions.mccbackend.controller;

import nl.cimsolutions.mccbackend.model.Humidity;
import nl.cimsolutions.mccbackend.model.Location;
import nl.cimsolutions.mccbackend.model.Voyager;
import nl.cimsolutions.mccbackend.model.VoyagerTempResponse;
import nl.cimsolutions.mccbackend.repository.LocationRepository;
import nl.cimsolutions.mccbackend.repository.VoyagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/voyagers")
@CrossOrigin
public class VoyagerController {

    @Autowired
    private VoyagerRepository voyagerRepository;

    @Autowired
    private LocationRepository locationRepository;

    @GetMapping("")
    public Page<Voyager> getVoyagers(Pageable pageable) {
        return voyagerRepository.findAll(pageable);
    }

    @GetMapping("/{voyagerId}")
    public Optional<Voyager> getVoyager(Pageable pageable, @PathVariable Long voyagerId) {
        return voyagerRepository.findById(voyagerId);
    }

    @PostMapping("")
    public Voyager createVoyager(@Valid @RequestBody Voyager voyager) {
        return voyagerRepository.save(voyager);
    }

    @GetMapping("/{voyagerId}/temperature")
    public List<VoyagerTempResponse> getTemperaturePerHour(@PathVariable Long voyagerId) {
        return locationRepository.avgTempPerHour(voyagerId);
    }

}
