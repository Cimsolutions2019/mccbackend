package nl.cimsolutions.mccbackend.repository;

import nl.cimsolutions.mccbackend.model.Research;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResearchRepository extends JpaRepository<Research, Long> {

    @Query("SELECT r FROM Research r WHERE r.status = 'CREATED' or r.status = 'STARTED'")
    List<Research> findAllActiveResearches();

}
