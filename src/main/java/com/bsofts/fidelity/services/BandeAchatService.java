package com.bsofts.fidelity.services;

import com.bsofts.fidelity.exceptions.ResourceNotFoundException;
import com.bsofts.fidelity.models.BandeAchat;
import com.bsofts.fidelity.models.Client;
import com.bsofts.fidelity.models.EBandeAchat;
import com.bsofts.fidelity.repositories.BandeAchatRepository;
import com.bsofts.fidelity.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class BandeAchatService {
    @Autowired
    BandeAchatRepository bandeAchatRepository;
    @Autowired
    ClientRepository clientRepository;



    // Récupérer la liste des bande achat par cl

    public List<BandeAchat> getAllBandeAchat()
    {
        return this.bandeAchatRepository.findAll();
    }

    // Récupérer liste bande achat by client
    public List<BandeAchat> findBandeAchatByClient (Long id)
    {
       Optional<Client> client = this.clientRepository.findById(id);
        if (client.isPresent()) {
            Client existingClient = client.orElseThrow(() -> new ResourceNotFoundException("Client not found"));
          return this.bandeAchatRepository.findAllByClient(existingClient);

        } else {
            throw new ResourceNotFoundException("Client not found");
        }

       /* return  this.bandeAchatRepository.findBandeAchatBylient(id);*/


        }



        //creation bande
        public String creationBande (Long id, int nombreDePoint) {

            Optional<Client> client = this.clientRepository.findById(id);
            if (client.isPresent()) {
                Client existingClient = client.orElseThrow(() -> new ResourceNotFoundException("Client not found"));

                //calcul pt
                int pointEchange = existingClient.getCategorie().getNombre_point_retrait();
                double montantEchange = existingClient.getCategorie().getMontant_retrait();
                if (nombreDePoint<= existingClient.getNombre_points()) {
                    try {
                        double montantCalculee = (double) (nombreDePoint * montantEchange) / pointEchange;

                        //creation de la bande d'achat
                        //generer code
                        Random random = new Random();
                        int code = 1000 + random.nextInt(9999);
                        //generer date creation
                        LocalDateTime dateCreation = LocalDateTime.now();
                        //generer date expiration
                        LocalDateTime dateExpiration = dateCreation.plusDays(10);
                        BandeAchat bandeAchat = new BandeAchat(EBandeAchat.VALIDE, code, dateExpiration, montantCalculee, dateCreation, existingClient);
                        //save bande achat
                        bandeAchatRepository.save(bandeAchat);
                        //update solde
                        existingClient.setNombre_points(existingClient.getNombre_points() - nombreDePoint);

                    } catch (ArithmeticException e) {
                        System.out.println("division par zero impossible");
                    }
                    // save existingClient in the database
                    this.clientRepository.save(existingClient);


                    // return statement
                    return "  cration bande  successfully done!";
                }else {
                    return ("vous ne pouvez pas transformer un nombre superieur a votre solde ");
                }

            } else {
                throw new ResourceNotFoundException("Client not found");
            }


        }

        // recuperer les bande valide

    public List<BandeAchat> findBandeAchatValideByClient (Long id)
    {
       return bandeAchatRepository.findByClientAndEtat(id);

    }








}
