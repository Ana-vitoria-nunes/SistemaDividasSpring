package org.example.repository;


import org.example.model.Card;
import org.example.model.Debts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DebtsRepository extends JpaRepository<Debts,String> {
    Optional<Debts> findByExternalIdDebts(String externalIdDebts);
}
