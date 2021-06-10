package com.bsofts.fidelity.controllers;

import com.bsofts.fidelity.domain.Mail;
import com.bsofts.fidelity.exceptions.EmailAlreadyUsedException;
import com.bsofts.fidelity.models.Administrateur;
import com.bsofts.fidelity.models.Manager;
import com.bsofts.fidelity.payload.responses.MessageResponse;
import com.bsofts.fidelity.services.ManagerService;
import com.bsofts.fidelity.services.SendMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/manager")
@CrossOrigin(origins = "*")
public class ManagerController {
    @Autowired
    ManagerService managerService;



    // Ajouter un Manager
    @PostMapping("/")
    public ResponseEntity<?> saveNewManger(@RequestBody Manager manager) throws EmailAlreadyUsedException {

        String  message =  this.managerService.addmanager(manager);



        return new ResponseEntity<>(new MessageResponse(message), HttpStatus.CREATED);
    }
    // Récupérer la liste des Managers
    @GetMapping("/")
    public ResponseEntity<List<Manager>> getAllManagers()
    {
        List<Manager> listManagers = this.managerService.getAllManager();
        return new ResponseEntity<>(listManagers, HttpStatus.OK);
    }

    // Récupérer un Manager par son id
    @GetMapping("/{id}")
    public ResponseEntity<?> findMangerByID(@PathVariable("id") Long id)
    {
        Manager manager = this.managerService.findManagerByID(id);
        return new ResponseEntity<>(manager, HttpStatus.OK);
    }

    // Modifier data  manager
    @PutMapping("/donnePersonnel/{email}")
    public ResponseEntity<MessageResponse> updateManagerDataByEmail(@PathVariable("email") String email, @RequestBody Manager manager)
    {
        String message = this.managerService.updateManagerDataByEmail(email, manager);
        return new ResponseEntity<>(new MessageResponse(message), HttpStatus.OK);
    }
    // Modifier data confid candidat
    @PutMapping("/donneeConfid/{email}")
    public ResponseEntity<MessageResponse> updateManagerDataConfidByEmail(@PathVariable("email") String email, @RequestBody Manager manager)
    {
        String message = this.managerService.updateManagerDataConfidByEmail(email, manager);
        return new ResponseEntity<>(new MessageResponse(message), HttpStatus.OK);
    }
    //find manager by email
    @GetMapping("/email/{email}")
    public ResponseEntity<?> findManagerByEmail(@PathVariable("email") String email)
    {
        Manager manager = this.managerService.findManagerByEmail(email);
        return new ResponseEntity<>(manager, HttpStatus.OK);
    }



    //modifier manager par super admin
    @PutMapping("/{id}")
    public ResponseEntity<MessageResponse> updateManagerByID(@PathVariable("id") Long id, @RequestBody Manager manager) throws EmailAlreadyUsedException {
        String message = this.managerService.updateManagerByID(id,manager);
        return new ResponseEntity<>(new MessageResponse(message), HttpStatus.OK);
    }


    // Changer etat manager  (activation et desactivation)
    @PutMapping("/etat/{id}")
    public ResponseEntity<MessageResponse> changeEtatManagerByID(@PathVariable("id") Long id)
    {
        String message = this.managerService.changeEtatManagerByID(id);
        return new ResponseEntity<>(new MessageResponse(message), HttpStatus.OK);
    }

}
