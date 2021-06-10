package com.bsofts.fidelity.repositories;

import com.bsofts.fidelity.models.BandeAchat;
import com.bsofts.fidelity.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BandeAchatRepository extends JpaRepository<BandeAchat,Long> {
    //find bande Achat  by code
    Optional<BandeAchat> findByCode(int code);

    List<BandeAchat> findAllByClient(Client client);

    //finde bande where etat=valide
    @Query("select c from BandeAchat c where  c.etat='VALIDE' and c.client.id =:x")
            public List<BandeAchat> findByClientAndEtat(@Param("x") Long id);




}
