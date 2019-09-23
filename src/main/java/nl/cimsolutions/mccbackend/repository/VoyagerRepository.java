package nl.cimsolutions.mccbackend.repository;

import nl.cimsolutions.mccbackend.model.Voyager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoyagerRepository extends JpaRepository<Voyager, Long> {
}