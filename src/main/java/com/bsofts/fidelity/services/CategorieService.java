package com.bsofts.fidelity.services;

import com.bsofts.fidelity.exceptions.ResourceNotFoundException;
import com.bsofts.fidelity.models.Categorie;
import com.bsofts.fidelity.models.Role;
import com.bsofts.fidelity.repositories.CategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CategorieService {
    @Autowired
    CategorieRepository categorieRepository;
    // Ajout categorie
    public Categorie saveNewCategorie(Categorie categorie)
    {

        return this.categorieRepository.save(categorie);
    }
    // Récupérer tous les categorie
    public List<Categorie> getAllCategories()
    {
        return this.categorieRepository.findAll();
    }
    // Récupérer
    public Categorie findCategorieByID(Long id)
    {
        Optional<Categorie> categorieData = this.categorieRepository.findById(id);
        // Return statement if categorie exist or null
        return categorieData.orElseThrow(() -> new ResourceNotFoundException(
                "Categorie not found"));

    }
    //update
    public String updateCategorieByID(Long id, Categorie categorie)
    {
        Optional<Categorie> categorieData = this.categorieRepository.findById(id);
        if (categorieData.isPresent()) {
            Categorie existingCategorie = categorieData.orElse(null);
            existingCategorie.setMontant_attribution(categorie.getMontant_attribution());
            existingCategorie.setNombre_point_attribution(categorie.getNombre_point_attribution());
            existingCategorie.setMontant_retrait(categorie.getMontant_retrait());
            existingCategorie.setNombre_point_retrait(categorie.getNombre_point_retrait());
            // save existing User in the database
            this.categorieRepository.save(existingCategorie);
            // return statement
            return "categorie updated successfully!";
        } else {
            throw new ResourceNotFoundException("Categorie not found");
        }
    }
    //update formule attribution
    public String updateCategorieFaByID(Long id, Categorie categorie)
    {
        Optional<Categorie> categorieData = this.categorieRepository.findById(id);
        if (categorieData.isPresent()) {
            Categorie existingCategorie = categorieData.orElse(null);

            existingCategorie.setMontant_attribution(categorie.getMontant_attribution());
            existingCategorie.setNombre_point_attribution(categorie.getNombre_point_attribution());

            // save existing User in the database
            this.categorieRepository.save(existingCategorie);
            // return statement
            return "categorie updated successfully!";
        } else {
            throw new ResourceNotFoundException("Categorie not found");
        }
    }
    //update formule echange
    public String updateCategorieFeByID(Long id, Categorie categorie)
    {
        Optional<Categorie> categorieData = this.categorieRepository.findById(id);
        if (categorieData.isPresent()) {
            Categorie existingCategorie = categorieData.orElse(null);

            existingCategorie.setMontant_retrait(categorie.getMontant_retrait());
            existingCategorie.setNombre_point_retrait(categorie.getNombre_point_retrait());

            // save existing User in the database
            this.categorieRepository.save(existingCategorie);
            // return statement
            return "categorie updated successfully!";
        } else {
            throw new ResourceNotFoundException("Categorie not found");
        }
    }



}
