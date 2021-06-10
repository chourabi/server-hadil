package com.bsofts.fidelity.controllers;

import com.bsofts.fidelity.models.TransactionAchat;
import com.bsofts.fidelity.payload.responses.MessageResponse;
import com.bsofts.fidelity.services.TransactionAchatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactionAchat")
@CrossOrigin(origins = "*")
public class TransactionAchatController {

    @Autowired
    TransactionAchatService transactionAchatService;
    //achat espece
    @PostMapping("/achatEspece/{id}/{montant}")
    public ResponseEntity<MessageResponse> AchatEspece(@PathVariable("id") Long id, @PathVariable("montant") double montant)
    {
        String message = this.transactionAchatService.achatEspece(id, montant);
        return new ResponseEntity<>(new MessageResponse(message), HttpStatus.OK);
    }
    //save transactionAchat par bande
    @PostMapping("/achatBande/{id}/{montant}")
    public ResponseEntity<?> AchatParBande(@PathVariable("id") Long id, @PathVariable("montant") double montant)
    {
        TransactionAchat transactionAchat = this.transactionAchatService.transctionAchatBande(id, montant);
        return new ResponseEntity<>(transactionAchat, HttpStatus.OK);
    }
   //save details
    @PostMapping("/detailAchatBande/{idTransaction}/{idBande}")
    public ResponseEntity<MessageResponse> detailsAchats(@PathVariable("idTransaction") Long idT, @PathVariable("idBande") Long idB)
    {
        String message = this.transactionAchatService.saveDetails(idT, idB);
        return new ResponseEntity<>(new MessageResponse(message), HttpStatus.OK);
    }






}
