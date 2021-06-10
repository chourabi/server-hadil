package com.bsofts.fidelity.services;

import com.bsofts.fidelity.exceptions.ResourceNotFoundException;
import com.bsofts.fidelity.models.*;
import com.bsofts.fidelity.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class TransactionAchatService {
    @Autowired
    ClientRepository clientRepository;
    
    @Autowired
    UserRepository userRepo;
    
    @Autowired
    NotificationsRepository notificationsRepository;
    
    
    @Autowired
    TransactionAchatRepository transactionAchatRepository;
    @Autowired
    DetailAchatRepository detailAchatRepository;
    @Autowired
    CategorieRepository categorieRepository;
    @Autowired
    BandeAchatRepository bandeAchatRepository;

    //attribution des points
    public String achatEspece(Long id, double montant) {
        Optional<Client> client = this.clientRepository.findById(id);
        
        
        User current =  this.userRepo.findById(client.get().getId()).get();
        
        long millis=System.currentTimeMillis(); 
       
		
        		
        
        if (client.isPresent()) {
            Client existingClient = client.orElseThrow(() -> new ResourceNotFoundException("Client not found"));

            //calcul pt
            double montantAtt = existingClient.getCategorie().getMontant_attribution();
            int pointAtt = existingClient.getCategorie().getNombre_point_attribution();
            try {
                int nombreDePointCalcule = (int) ((montant * pointAtt) / montantAtt);
                TransactionAchat transactionAchat=new TransactionAchat(new Date(), montant,existingClient);
                DetailAchat detailAchat = new DetailAchat(nombreDePointCalcule,ModeAchat.ACHAT_ESPECE,transactionAchat);
                transactionAchatRepository.save(transactionAchat);
                
                
                
                // hundle notification
                Notifications test = new Notifications();
                test.setTitle("New transaction");
                test.setMessage("viva hello world ".concat( Integer.toString(pointAtt) ));
                test.setSeen(false);
                test.setAdddate(   new Date(millis)  );
                test.setUser(current);
                
                this.notificationsRepository.save(test);
                
                
                //end hundle notifications
                
                
                detailAchatRepository.save(detailAchat);
                //update solde
                existingClient.setNombre_points(existingClient.getNombre_points() + nombreDePointCalcule);

            } catch (ArithmeticException e) {
                System.out.println("division par zero impossible");
            }
            //update categorie
            if (existingClient.getNombre_points() > 500 && existingClient.getNiveau() == 0) {
                Categorie categorieClient = this.categorieRepository.findByName(ECategorie.SILVER)
                        .orElseThrow(() -> new ResourceNotFoundException("Error: Category  is not found."));
                existingClient.setCategorie(categorieClient);
                existingClient.setNiveau(1);

            } else if (existingClient.getNombre_points() > 1000 && (existingClient.getNiveau() == 0 || existingClient.getNiveau() == 1)) {
                Categorie categorieClient = this.categorieRepository.findByName(ECategorie.GOLD)
                        .orElseThrow(() -> new ResourceNotFoundException("Error: Category  is not found."));
                existingClient.setCategorie(categorieClient);
                existingClient.setNiveau(2);
            }
            // save existingClient in the database
            this.clientRepository.save(existingClient);

        } else {
            throw new ResourceNotFoundException("Client not found");
        }
        return "  transaction espece successfully saved!" ;

    }

    //save transactionAchat mode payement bande achat

    public TransactionAchat transctionAchatBande(Long id, double montant) {
        Optional<Client> client = this.clientRepository.findById(id);

        if (client.isPresent()) {
            Client existingClient = client.orElseThrow(() -> new ResourceNotFoundException("Client not found"));
            TransactionAchat transactionAchat=new TransactionAchat(new Date(), montant,existingClient);
            TransactionAchat transactionAchat1=this.transactionAchatRepository.save(transactionAchat);
            return transactionAchat1;
        } else {
            throw new ResourceNotFoundException("Client not found");
        }


    }
    //save details
    public String saveDetails (Long idtransaction ,Long idBande) {
        Optional<TransactionAchat> transactionAchat = this.transactionAchatRepository.findById(idtransaction);
        Optional<BandeAchat> bandeAchat1 = this.bandeAchatRepository.findById(idBande);


        if (transactionAchat.isPresent()) {
            TransactionAchat existingTransaction = transactionAchat.orElseThrow(() -> new ResourceNotFoundException("Transaction not found"));
            if (bandeAchat1.isPresent()) {
                 BandeAchat existingBande = bandeAchat1.orElseThrow(() -> new ResourceNotFoundException("bande not found"));

                DetailAchat detailAchat = new DetailAchat(0, ModeAchat.ACHAT_BANDE, existingTransaction, existingBande);

                detailAchatRepository.save(detailAchat);
                existingBande.setEtat(EBandeAchat.UTILISEE);
                bandeAchatRepository.save(existingBande);




            } else {
                throw new ResourceNotFoundException("bande not found");
            }

        } else {
            throw new ResourceNotFoundException("Client not found");
        }

        return "details saved";

    }


}
