package com.bsofts.fidelity.controllers;

import com.bsofts.fidelity.exceptions.EmailAlreadyUsedException;
import com.bsofts.fidelity.models.Client;
import com.bsofts.fidelity.payload.responses.MessageResponse;
import com.bsofts.fidelity.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
@CrossOrigin(origins = "*")
public class ClientController {
    @Autowired
    ClientService clientService;

    // Ajouter un client
    @PostMapping("/")
    public ResponseEntity<?> saveNewClient(@RequestBody Client client) throws EmailAlreadyUsedException {
        String  message =  this.clientService.addClient(client);
        return new ResponseEntity<>(new MessageResponse(message), HttpStatus.CREATED);
    }

    // Récupérer la liste des clients
    @GetMapping("/")
    public ResponseEntity<List<Client>> getAllClients()
    {
        List<Client> listClients = this.clientService.getAllClients();
        return new ResponseEntity<>(listClients, HttpStatus.OK);
    }

    // Récupérer un client par son id
    @GetMapping("/{id}")
    public ResponseEntity<?> findClientByID(@PathVariable("id") Long id)
    {
        Client client = this.clientService.findClientByID(id);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    // Modifier un compte client
    @PutMapping("/compte/{id}")
    public ResponseEntity<MessageResponse> updateClientByID(@PathVariable("id") Long id, @RequestBody Client client) throws EmailAlreadyUsedException {
        String message = this.clientService.updateClientCompteByID(id, client);
        return new ResponseEntity<>(new MessageResponse(message), HttpStatus.OK);
    }
    // Modifier un info personelle client
    @PutMapping("/informationPerso/{id}")
    public ResponseEntity<MessageResponse> updateClientPersoByID(@PathVariable("id") Long id, @RequestBody Client client)
    {
    	System.out.print("ninja call "+client.getDate_naissance().toString());
    	
    	
    	
        String message = this.clientService.updateClientPersoByID(id, client);
        return new ResponseEntity<>(new MessageResponse(message), HttpStatus.OK);
    }


    // Changer etat Administrateur (activation et desactivation)
    @PutMapping("/etat/{id}")
    public ResponseEntity<MessageResponse> changeEtatClientByID(@PathVariable("id") Long id)
    {
        String message = this.clientService.changeEtatClientByID(id);
        return new ResponseEntity<>(new MessageResponse(message), HttpStatus.OK);
    }
}
