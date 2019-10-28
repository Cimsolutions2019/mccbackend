package nl.cimsolutions.mccbackend.repository;

import nl.cimsolutions.mccbackend.model.WeatherStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeatherStationRepository extends JpaRepository<WeatherStation, Long> {

    @Query("SELECT w FROM WeatherStation w WHERE data_source_id = :dataSourceId")
    List<WeatherStation> findWeatherStationsByDataSource(@Param("dataSourceId") Long dataSourceId);

}
