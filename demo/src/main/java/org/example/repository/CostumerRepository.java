package org.example.repository;



import org.example.model.Costumer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.rmi.server.UID;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CostumerRepository extends JpaRepository<Costumer,String> {

   Optional<Costumer> findByExternalId(String externalIdCostumer);
}
