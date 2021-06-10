package com.bsofts.fidelity.repositories;

import com.bsofts.fidelity.models.ERole;
import com.bsofts.fidelity.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface RoleRepository extends JpaRepository <Role,Long> {
    // find Role by name
    Optional<Role> findByName(ERole name);

}
