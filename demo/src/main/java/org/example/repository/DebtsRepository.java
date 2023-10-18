package org.example.repository;

import com.example.demo.model.Debts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DebtsRepository extends JpaRepository<Debts,Long> {
}
