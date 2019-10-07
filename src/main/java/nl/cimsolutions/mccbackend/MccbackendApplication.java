package nl.cimsolutions.mccbackend;


import nl.cimsolutions.mccbackend.model.*;
import nl.cimsolutions.mccbackend.repository.DataSourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.io.IOException;
import java.text.ParseException;

@SpringBootApplication
@EnableJpaAuditing
public class MccbackendApplication implements CommandLineRunner {

    @Autowired
    DataSourceRepository dataSourceRepository;

	public static void main(String[] args) throws IOException, ParseException {
		SpringApplication.run(MccbackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//        DataSource knmi = new DataSource("KNMI", "www.knmi.nl");
//        DataSource luchtmeetnet = new DataSource("Luchtmeetnet", "www.luchtmeetnet.nl");
//
//        WeatherStation weatherStation1 = new WeatherStation("Amsterdam", "1234", 1.23, 3.456);
//        WeatherStation weatherStation2 = new WeatherStation("Groningen", "5678", 1.23, 3.456);
//        knmi.getWeatherStations().add(weatherStation1);
//        luchtmeetnet.getWeatherStations().add(weatherStation2);
//        dataSourceRepository.save(knmi);
//        dataSourceRepository.save(luchtmeetnet);
//
    }



}
