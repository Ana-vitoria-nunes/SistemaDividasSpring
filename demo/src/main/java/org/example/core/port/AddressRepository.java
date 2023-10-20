package org.example.core.port;


import org.example.core.domain.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address,String> {
    Optional<Address> findByExternalIdAddress(String externalIdAddress);
}
