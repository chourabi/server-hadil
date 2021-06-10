package com.bsofts.fidelity.repositories;

import com.bsofts.fidelity.models.Client;
import org.hibernate.cfg.JPAIndexHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends RevisionRepository<Client,Long,Integer> ,JpaRepository<Client,Long> {
    // return True if email already exist
    boolean existsByEmail(String email);
    // find user by email address
    Optional<Client> findByEmail(String email);
    //find user by tel
    Optional<Client> findByTelephone(String telephone);

}
