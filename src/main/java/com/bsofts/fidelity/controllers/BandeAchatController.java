package com.bsofts.fidelity.controllers;

import com.bsofts.fidelity.models.BandeAchat;
import com.bsofts.fidelity.models.Categorie;
import com.bsofts.fidelity.models.Client;
import com.bsofts.fidelity.payload.responses.MessageResponse;
import com.bsofts.fidelity.services.BandeAchatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bandeAchat")
@CrossOrigin(origins = "*")
public class BandeAchatController {
    @Autowired
    BandeAchatService bandeAchatService;

    // Récupérer la liste des bandes achats by client
    @GetMapping("/{id}")
    public ResponseEntity<List<BandeAchat>> getBandeAchatByClient(@PathVariable("id") Long id)
    {
        List<BandeAchat> listBandeAchats = this.bandeAchatService.findBandeAchatByClient(id);
        return new ResponseEntity<>(listBandeAchats, HttpStatus.OK);
    }

    // creation bande
    @PostMapping("/{id}/{nbrePoints}")
    public ResponseEntity<MessageResponse> creationBande(@PathVariable("id") Long id,@PathVariable("nbrePoints") int nbrePoint)
    {
        String message = this.bandeAchatService.creationBande(id,nbrePoint);
        return new ResponseEntity<MessageResponse>(new MessageResponse(message), HttpStatus.OK);
    }

    //recuperer les bandes valides
    @GetMapping("/bandeValide/{id}")
    public ResponseEntity<List<BandeAchat>> getBandeAchatValideByClient(@PathVariable("id") Long id)
    {
        List<BandeAchat> listBandeAchatsValide = this.bandeAchatService.findBandeAchatValideByClient(id);
        return new ResponseEntity<>(listBandeAchatsValide, HttpStatus.OK);
    }





}
