package org.example.core.port;


import org.example.core.domain.model.Debts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DebtsRepository extends JpaRepository<Debts,String> {
    Optional<Debts> findByExternalIdDebts(String externalIdDebts);
}
