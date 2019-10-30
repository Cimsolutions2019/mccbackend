package nl.cimsolutions.mccbackend.repository;

import nl.cimsolutions.mccbackend.model.Location;
import nl.cimsolutions.mccbackend.payload.VoyagerTempResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

    @Query("SELECT NEW nl.cimsolutions.mccbackend.payload.VoyagerTempResponse(date_trunc('hour', l.time) as time, avg(t.value))" +
            "FROM Location l " +
            "INNER JOIN l.temperature t " +
            "WHERE voyager_id = :id and research_id = :researchId GROUP BY 1 ORDER BY 1")
    List<VoyagerTempResponse> avgTempPerHour(@Param("id") Long voyagerId, @Param("researchId") Long researchId);

    @Query("SELECT l FROM Location l WHERE voyager_id = :voyagerId and research_id = :researchId and cast(time as date) = :date and cast(time as date) <= :date2 ORDER BY 1")
    List<Location> findByResearchIdAndVoyagerId(@Param("researchId") Long researchId, @Param("voyagerId") Long voyagerId, @Param("date") Date date, @Param("date2") Date date2);

    @Query("SELECT NEW nl.cimsolutions.mccbackend.payload.VoyagerTempResponse(date_trunc('hour', l.time) as time, avg(t.value))" +
            "FROM Location l " +
            "INNER JOIN l.temperature t " +
            "WHERE voyager_id = :voyagerId and research_id = :researchId and cast(time as date) >= :date and cast(time as date) <= :date2" +
            " GROUP BY 1 ORDER BY 1")
    List<VoyagerTempResponse> avgTempPerHourPerDay(@Param("voyagerId") Long voyagerId, @Param("researchId") Long researchId,  @Param("date") Date date, @Param("date2") Date date2);

    @Query("SELECT NEW nl.cimsolutions.mccbackend.payload.VoyagerTempResponse(date_trunc('day', l.time) as time, avg(t.value))" +
            "FROM Location l " +
            "INNER JOIN l.temperature t " +
            "WHERE voyager_id = :voyagerId and research_id = :researchId and cast(time as date) >= :date and cast(time as date) <= :date2" +
            " GROUP BY 1 ORDER BY 1")
    List<VoyagerTempResponse> avgTempPerDay(@Param("voyagerId") Long voyagerId, @Param("researchId") Long researchId,  @Param("date") Date date, @Param("date2") Date date2);

    @Query("SELECT NEW nl.cimsolutions.mccbackend.payload.VoyagerTempResponse(date_trunc('hour', l.time) as time, avg(t.value))" +
            "FROM Location l " +
            "INNER JOIN l.humidity t " +
            "WHERE voyager_id = :voyagerId and research_id = :researchId and cast(time as date) >= :date and cast(time as date) <= :date2" +
            " GROUP BY 1 ORDER BY 1")
    List<VoyagerTempResponse> avgHumidityPerHourPerDay(@Param("voyagerId") Long voyagerId, @Param("researchId") Long researchId,  @Param("date") Date date, @Param("date2") Date date2);

    @Query("SELECT NEW nl.cimsolutions.mccbackend.payload.VoyagerTempResponse(date_trunc('day', l.time) as time, avg(t.value))" +
            "FROM Location l " +
            "INNER JOIN l.humidity t " +
            "WHERE voyager_id = :voyagerId and research_id = :researchId and cast(time as date) >= :date and cast(time as date) <= :date2" +
            " GROUP BY 1 ORDER BY 1")
    List<VoyagerTempResponse> avgHumidityPerDay(@Param("voyagerId") Long voyagerId, @Param("researchId") Long researchId,  @Param("date") Date date, @Param("date2") Date date2);

    @Query("SELECT NEW nl.cimsolutions.mccbackend.payload.VoyagerTempResponse(l.time as time, avg(t.value))" +
            "FROM Location l " +
            "INNER JOIN l.temperature t " +
            "WHERE voyager_id = :voyagerId and research_id = :researchId and cast(time as date) >= :date and cast(time as date) <= :date2" +
            " GROUP BY 1 ORDER BY 1")
    List<VoyagerTempResponse> tempPerMinutePerDay(@Param("voyagerId") Long voyagerId, @Param("researchId") Long researchId,  @Param("date") Date date, @Param("date2") Date date2);

    @Query("SELECT NEW nl.cimsolutions.mccbackend.payload.VoyagerTempResponse( l.time as time, avg(t.value))" +
            "FROM Location l " +
            "INNER JOIN l.humidity t " +
            "WHERE voyager_id = :voyagerId and research_id = :researchId and cast(time as date) >= :date and cast(time as date) <= :date2" +
            " GROUP BY 1 ORDER BY 1")
    List<VoyagerTempResponse> humidityPerMinutePerDay(@Param("voyagerId") Long voyagerId, @Param("researchId") Long researchId,  @Param("date") Date date, @Param("date2") Date date2);

    @Query("SELECT l FROM Location l WHERE research_id = :researchId and data_source_id = :dataSourceId")
    List<Location> findByResearchIdAndDataSourceId(@Param("researchId") Long researchId, @Param("dataSourceId") Long dataSourceId);
}
