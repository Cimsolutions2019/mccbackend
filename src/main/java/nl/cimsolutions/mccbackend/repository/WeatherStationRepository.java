package nl.cimsolutions.mccbackend.repository;

import nl.cimsolutions.mccbackend.model.Voyager;
import nl.cimsolutions.mccbackend.model.WeatherStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherStationRepository extends JpaRepository<WeatherStation, Long> {

}
