package com.bsofts.fidelity.repositories;

import com.bsofts.fidelity.models.Categorie;
import com.bsofts.fidelity.models.ECategorie;
import com.bsofts.fidelity.models.ERole;
import com.bsofts.fidelity.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategorieRepository extends JpaRepository<Categorie,Long> {
    // find category by name
    Optional<Categorie> findByName(ECategorie name);
}
