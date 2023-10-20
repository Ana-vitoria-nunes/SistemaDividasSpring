package org.example.core.port;


import org.example.core.domain.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card,String> {
    Optional<Card> findByExternalIdCard(String externalIdCard);
}
