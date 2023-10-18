package org.example.repository;


import org.example.model.Debts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DebtsRepository extends JpaRepository<Debts,Long> {
}
