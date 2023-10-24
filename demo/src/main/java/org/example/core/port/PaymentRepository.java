package org.example.core.port;


import org.example.core.domain.model.Card;
import org.example.core.domain.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,String> {
    Optional<Payment> findByExternalIdPayment(String externalIdPayment);
}
