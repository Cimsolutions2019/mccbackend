package nl.cimsolutions.mccbackend.repository;

import nl.cimsolutions.mccbackend.model.DataSource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataSourceRepository extends JpaRepository<DataSource, Long> {
}
