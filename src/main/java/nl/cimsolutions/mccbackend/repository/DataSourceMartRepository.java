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
public interface DataSourceMartRepository extends JpaRepository<DimLoc, Long> {

    @Query("SELECT NEW nl.cimsolutions.mccbackend.payload.DataSourceMeasurementResponse(t.time, cast(f.value as double))" +
            "FROM  FactSource f " +
            "INNER JOIN  f.dimCom c " +
            "INNER JOIN f.dimLoc l " +
            "INNER JOIN f.dimTime t " +
            "WHERE c.name = :sensor and cast(time as date) = :date " +
            "and f.data_source_id = :dataSourceId and f.weather_station_id = :weatherStationId ")
    List<DataSourceMeasurementResponse> findAllData(@Param("date") Date date, @Param("sensor") String sensor,
                                                    @Param("weatherStationId") String weatherStationId,
                                                    @Param("dataSourceId") Long dataSourceId);

    @Query("SELECT NEW nl.cimsolutions.mccbackend.payload.DataSourceMeasurementResponse(date_trunc('hour', t.time) as time, avg(cast(f.value as double)) as value)" +
            "FROM  FactSource f " +
            "INNER JOIN  f.dimCom c " +
            "INNER JOIN f.dimLoc l " +
            "INNER JOIN f.dimTime t " +
            "WHERE c.name = :sensor and cast(time as date) >= :date and cast(time as date) <= :date2 " +
            "and f.data_source_id = :dataSourceId and f.weather_station_id LIKE '%391%' GROUP BY 1 ORDER BY 1")
    List<DataSourceMeasurementResponse> findavgDataPerHour(@Param("date") Date date, @Param("date2") Date date2, @Param("sensor") String sensor,
                                                    @Param("dataSourceId") Long dataSourceId);

    @Query("SELECT NEW nl.cimsolutions.mccbackend.payload.DataSourceMeasurementResponse(date_trunc('day', t.time), avg(cast(f.value as double)) as value)" +
            "FROM  FactSource f " +
            "INNER JOIN  f.dimCom c " +
            "INNER JOIN f.dimLoc l " +
            "INNER JOIN f.dimTime t " +
            "WHERE c.name = :sensor and cast(time as date) >= :date and cast(time as date) <= :date2 " +
            "and f.data_source_id = :dataSourceId and f.weather_station_id LIKE '%391%' GROUP BY 1 ORDER BY 1")
    List<DataSourceMeasurementResponse> findavgDataPerDay(@Param("date") Date date, @Param("date2") Date date2, @Param("sensor") String sensor,
                                                           @Param("dataSourceId") Long dataSourceId);
}
