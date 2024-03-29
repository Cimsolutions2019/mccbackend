package nl.cimsolutions.mccbackend.repository;

import nl.cimsolutions.mccbackend.model.Voyager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.OrderBy;
import java.util.List;

@Repository
public interface VoyagerRepository extends JpaRepository<Voyager, Long> {

}