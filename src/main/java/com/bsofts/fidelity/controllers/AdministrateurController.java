package com.bsofts.fidelity.controllers;

import com.bsofts.fidelity.domain.Mail;
import com.bsofts.fidelity.exceptions.EmailAlreadyUsedException;
import com.bsofts.fidelity.models.Administrateur;
import com.bsofts.fidelity.models.User;
import com.bsofts.fidelity.payload.responses.MessageResponse;
import com.bsofts.fidelity.services.AdministrateurService;
import com.bsofts.fidelity.services.SendMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/administrateur")
@CrossOrigin(origins = "*")
public class AdministrateurController {




       @Autowired
       AdministrateurService administrateurService;



    // Ajouter un administrateur
    @PostMapping("/")
    public ResponseEntity<?> saveNewAdministrateur(@RequestBody Administrateur administrateur) throws EmailAlreadyUsedException {

        String  message =  this.administrateurService.addAdministrateur(administrateur);



        return new ResponseEntity<>(new MessageResponse(message), HttpStatus.CREATED);
    }
    // Récupérer la liste des administrateurs
    @GetMapping("/")
    public ResponseEntity<List<Administrateur>> getAllAdministrateurs()
    {
        List<Administrateur> listAdministrateurs = this.administrateurService.getAllAdministrateurs();
        return new ResponseEntity<>(listAdministrateurs, HttpStatus.OK);
    }

    // Récupérer un Administrateur par son id
    @GetMapping("/{id}")
    public ResponseEntity<?> findAdministrateurByID(@PathVariable("id") Long id)
    {
        Administrateur administrateur = this.administrateurService.findAdministrateurByID(id);
        return new ResponseEntity<>(administrateur, HttpStatus.OK);
    }

    // modif par super Admin
    @PutMapping("/{id}")
    public ResponseEntity<MessageResponse> updateAdministrateurByID(@PathVariable("id") Long id, @RequestBody Administrateur administrateur) throws EmailAlreadyUsedException {
        String message = this.administrateurService.updateAdministrateurByID(id,administrateur);
        return new ResponseEntity<>(new MessageResponse(message), HttpStatus.OK);
    }


    // Changer etat superAdministrateur (activation et desactivation)
    @PutMapping("/etat/{id}")
    public ResponseEntity<MessageResponse> changeEtatSuperAdministrateurByID(@PathVariable("id") Long id)
    {
        String message = this.administrateurService.changeEtatAdministrateurByID(id);
        return new ResponseEntity<>(new MessageResponse(message), HttpStatus.OK);
    }
    //find admin by email
    @GetMapping("/email/{email}")
    public ResponseEntity<?> findAdministrateurByEmail(@PathVariable("email") String email)
    {
        Administrateur administrateur = this.administrateurService.findAdministrateurByEmail(email);
        return new ResponseEntity<>(administrateur, HttpStatus.OK);
    }
    // update admin by email
    // Modifier data confid candidat
    @PutMapping("/compte/{email}")
    public ResponseEntity<MessageResponse> updateAdministrateurCompteByEmail(@PathVariable("email") String email, @RequestBody Administrateur administrateur) throws EmailAlreadyUsedException {
        String message = this.administrateurService.updateAdministrateurCompteByEmail(email, administrateur);
        return new ResponseEntity<>(new MessageResponse(message), HttpStatus.OK);
    }





}
