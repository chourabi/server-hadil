package com.bsofts.fidelity.controllers;


import com.bsofts.fidelity.models.SuperAdministrateur;
import com.bsofts.fidelity.payload.responses.MessageResponse;
import com.bsofts.fidelity.services.SuperAdministrateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/superAdministrateur")
@CrossOrigin(origins = "*")
public class SuperAdministrateurController {

    @Autowired
    SuperAdministrateurService superAdministrateurService;

    // Ajouter un superadministrateur
    @PostMapping("/")
    public ResponseEntity<?> saveNewSuperAdministrateur(@RequestBody SuperAdministrateur superAdministrateur)
    {
        String  message =  this.superAdministrateurService.addSuperAdministrateur(superAdministrateur);
        return new ResponseEntity<>(new MessageResponse(message), HttpStatus.CREATED);
    }

    // Récupérer la liste des administrateurs
    @GetMapping("/")
    public ResponseEntity<List<SuperAdministrateur>> getAllSuperAdministrateurs()
    {
        List<SuperAdministrateur> listSuperAdministrateurs = this.superAdministrateurService.getAllSuperAdministrateurs();
        return new ResponseEntity<>(listSuperAdministrateurs, HttpStatus.OK);
    }

    // Récupérer un superAdministrateur par son id
    @GetMapping("/{id}")
    public ResponseEntity<?> findSuperAdministrateurByID(@PathVariable("id") Long id)
    {
        SuperAdministrateur superAdministrateur = this.superAdministrateurService.findSuperAdministrateurByID(id);
        return new ResponseEntity<>(superAdministrateur, HttpStatus.OK);
    }

    // Modifier un superadministrateur
    @PutMapping("/{id}")
    public ResponseEntity<MessageResponse> updateSuperAdministrateurByID(@PathVariable("id") Long id, @RequestBody SuperAdministrateur superAdministrateur)
    {
        String message = this.superAdministrateurService.updateSuperAdministrateurByID(id, superAdministrateur);
        return new ResponseEntity<>(new MessageResponse(message), HttpStatus.OK);
    }

    // Changer etat superAdministrateur (activation et desactivation)
    @PutMapping("/etat/{id}")
    public ResponseEntity<MessageResponse> changeEtatSuperAdministrateurByID(@PathVariable("id") Long id)
    {
        String message = this.superAdministrateurService.changeEtatSuperAdministrateurByID(id);
        return new ResponseEntity<>(new MessageResponse(message), HttpStatus.OK);
    }

}
