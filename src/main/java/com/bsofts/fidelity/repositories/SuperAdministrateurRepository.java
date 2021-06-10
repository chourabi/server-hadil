package com.bsofts.fidelity.repositories;


import com.bsofts.fidelity.models.SuperAdministrateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SuperAdministrateurRepository extends JpaRepository<SuperAdministrateur,Long> {

    // return True if email already exist
    boolean existsByEmail(String email);
    // find user by email address
    Optional<SuperAdministrateur> findByEmail(String email);
}
