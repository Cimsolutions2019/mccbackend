package nl.cimsolutions.mccbackend.repository;

import nl.cimsolutions.mccbackend.model.DataSource;
import nl.cimsolutions.mccbackend.payload.DataSourceMeasurementResponse;
import nl.cimsolutions.mccbackend.payload.VoyagerTempResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DataSourceRepository extends JpaRepository<DataSource, Long> {

}
