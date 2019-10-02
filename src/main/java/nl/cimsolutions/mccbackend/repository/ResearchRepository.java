package nl.cimsolutions.mccbackend.repository;

import nl.cimsolutions.mccbackend.model.Location;
import nl.cimsolutions.mccbackend.model.Research;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResearchRepository extends JpaRepository<Research, Long> {

}
