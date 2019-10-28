package nl.cimsolutions.mccbackend.repository;

import nl.cimsolutions.mccbackend.model.DataMart.DimCom;
import nl.cimsolutions.mccbackend.model.DataMart.DimLoc;
import nl.cimsolutions.mccbackend.model.DataMart.DimTime;
import nl.cimsolutions.mccbackend.model.DataMart.FactSource;
import nl.cimsolutions.mccbackend.model.DataSource;
import nl.cimsolutions.mccbackend.payload.DataSourceMeasurementResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface DataSourceMartRepository extends JpaRepository<FactSource, Long> {

    @Query("SELECT NEW nl.cimsolutions.mccbackend.payload.DataSourceMeasurementResponse(t.time as time, f.value)" +
            "FROM  FactSource f " +
            "INNER JOIN  f.dimCom c " +
            "INNER JOIN f.dimLoc l " +
            "INNER JOIN f.dimTime t " +
            "WHERE c.component = :sensor and cast(time as date) = :date " +
            "and f.data_source_id = :dataSourceId and f.weather_station_id = :weatherStationId")
    List<DataSourceMeasurementResponse> findAllData(@Param("date") Date date, @Param("sensor") String sensor,
                                                    @Param("weatherStationId") String weatherStationId,
                                                    @Param("dataSourceId") Long dataSourceId);
}
