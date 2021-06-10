package com.bsofts.fidelity.controllers;

import com.bsofts.fidelity.models.Categorie;
import com.bsofts.fidelity.models.Role;
import com.bsofts.fidelity.payload.responses.MessageResponse;
import com.bsofts.fidelity.services.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@CrossOrigin(origins = "*")
public class CategorieController {
    @Autowired
    CategorieService categorieService;
    // Ajout Categorie
    @PostMapping("/")
    public ResponseEntity<Categorie> saveNewCategorie(@RequestBody Categorie categorie)
    {
        Categorie savedCategorie =  this.categorieService.saveNewCategorie(categorie);
        return new ResponseEntity<>(savedCategorie, HttpStatus.CREATED);
    }

    // Récupérer la liste des categories
    @GetMapping("/")
    public ResponseEntity<List<Categorie>> getAllCategories()
    {
        List<Categorie> listCategories = this.categorieService.getAllCategories();
        return new ResponseEntity<>(listCategories, HttpStatus.OK);
    }
    // Modifier categorie
    @PutMapping("/{id}")
    public ResponseEntity<MessageResponse> updateCategorieByID(@PathVariable("id") Long id, @RequestBody Categorie categorie)
    {
        String message = this.categorieService.updateCategorieByID(id, categorie);
        return new ResponseEntity<>(new MessageResponse(message), HttpStatus.OK);
    }
    //modifier formules attribution
    @PutMapping("/formulesAttribution/{id}")
    public ResponseEntity<MessageResponse> updateFormulesAttributionByID(@PathVariable("id") Long id, @RequestBody Categorie categorie)
    {
        String message = this.categorieService.updateCategorieFaByID(id, categorie);
        return new ResponseEntity<>(new MessageResponse(message), HttpStatus.OK);
    }
    //modifier formules echange
    @PutMapping("/formulesEchange/{id}")
    public ResponseEntity<MessageResponse> updateFormulesEchangeByID(@PathVariable("id") Long id, @RequestBody Categorie categorie)
    {
        String message = this.categorieService.updateCategorieFeByID(id, categorie);
        return new ResponseEntity<>(new MessageResponse(message), HttpStatus.OK);
    }
}
