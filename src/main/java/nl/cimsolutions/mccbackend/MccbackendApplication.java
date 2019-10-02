package nl.cimsolutions.mccbackend;

import nl.cimsolutions.mccbackend.datasource.KnmiCollector;
import nl.cimsolutions.mccbackend.model.Location;
import nl.cimsolutions.mccbackend.model.Research;
import nl.cimsolutions.mccbackend.model.Temperature;
import nl.cimsolutions.mccbackend.model.Voyager;
import nl.cimsolutions.mccbackend.model.types.VoyagerSensors;
import nl.cimsolutions.mccbackend.repository.LocationRepository;
import nl.cimsolutions.mccbackend.repository.ResearchRepository;
import nl.cimsolutions.mccbackend.repository.VoyagerRepository;
import nl.cimsolutions.mccbackend.util.DummyDataGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;

@SpringBootApplication
@EnableJpaAuditing
public class MccbackendApplication implements CommandLineRunner {

    @Autowired
    VoyagerRepository voyagerRepository;

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    ResearchRepository researchRepository;

	public static void main(String[] args) throws IOException, ParseException {
		SpringApplication.run(MccbackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception { }



}
