package org.example.repository;


import org.example.model.Address;
import org.example.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card,String> {
    Optional<Card> findByExternalIdCard(String id_externo);
}
