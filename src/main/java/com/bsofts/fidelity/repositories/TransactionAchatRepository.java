package com.bsofts.fidelity.repositories;

import com.bsofts.fidelity.models.TransactionAchat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionAchatRepository extends JpaRepository<TransactionAchat,Long> {
}
