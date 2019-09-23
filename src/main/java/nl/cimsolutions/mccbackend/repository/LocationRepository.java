package nl.cimsolutions.mccbackend.repository;

import nl.cimsolutions.mccbackend.model.Location;
import nl.cimsolutions.mccbackend.model.VoyagerTempResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

    @Query("SELECT NEW nl.cimsolutions.mccbackend.model.VoyagerTempResponse(date_trunc('hour', l.time) as time, avg(t.value)" +
            "FROM Location l " +
            "INNER JOIN l.temperature t " +
            "WHERE voyager_id = :id GROUP BY 1 ORDER BY 1")
    List<VoyagerTempResponse> avgTempPerHour(@Param("id") Long voyagerId);
}
