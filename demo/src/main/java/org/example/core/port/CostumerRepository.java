package org.example.core.port;



import org.example.core.domain.model.Costumer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CostumerRepository extends JpaRepository<Costumer,String> {

   Optional<Costumer> findByExternalId(String externalIdCostumer);
}
