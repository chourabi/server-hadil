package com.bsofts.fidelity.repositories;




import com.bsofts.fidelity.models.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ManagerRepository extends JpaRepository<Manager,Long> {
    // return True if email already exist
    boolean existsByEmail(String email);
    // find user by email address
    Optional<Manager> findByEmail(String email);


}
