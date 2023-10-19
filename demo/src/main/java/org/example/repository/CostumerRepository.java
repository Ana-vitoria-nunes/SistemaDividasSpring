package org.example.repository;



import org.example.model.Costumer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CostumerRepository extends JpaRepository<Costumer,Long> {

    Optional<Costumer> findByExternalId(String id_externo);
}
