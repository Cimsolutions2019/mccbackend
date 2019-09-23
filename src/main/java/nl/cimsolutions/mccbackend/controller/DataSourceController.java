package nl.cimsolutions.mccbackend.controller;

import nl.cimsolutions.mccbackend.model.DataSource;
import nl.cimsolutions.mccbackend.model.Voyager;
import nl.cimsolutions.mccbackend.repository.DataSourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/datasource")
@CrossOrigin
public class DataSourceController {

    @Autowired
    DataSourceRepository dataSourceRepository;

    @GetMapping("")
    public List<DataSource> getDataSource(Pageable pageable) {
        return dataSourceRepository.findAll();
    }

    @PostMapping("")
    public DataSource createDataSource(@Valid @RequestBody DataSource dataSource) {
        return dataSourceRepository.save(dataSource);
    }
}
