package com.bsofts.fidelity.repositories;

import com.bsofts.fidelity.models.DetailAchat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailAchatRepository extends JpaRepository<DetailAchat,Long> {
}
