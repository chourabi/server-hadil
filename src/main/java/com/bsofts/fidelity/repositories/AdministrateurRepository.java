package com.bsofts.fidelity.repositories;


import com.bsofts.fidelity.models.Administrateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdministrateurRepository extends JpaRepository<Administrateur,Long> {
    // return True if email already exist
    boolean existsByEmail(String email);
    // find user by email address
    Optional<Administrateur> findByEmail(String email);

}
