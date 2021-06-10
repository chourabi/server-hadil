package com.bsofts.fidelity.services;

import com.bsofts.fidelity.exceptions.EmailAlreadyUsedException;
import com.bsofts.fidelity.exceptions.ResourceNotFoundException;
import com.bsofts.fidelity.models.*;
import com.bsofts.fidelity.repositories.CategorieRepository;
import com.bsofts.fidelity.repositories.ClientRepository;
import com.bsofts.fidelity.repositories.RoleRepository;
import com.bsofts.fidelity.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ClientService {

    @Autowired
    CategorieRepository categorieRepository;
    @Autowired
    ClientRepository clientRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;
    // ajout client
    public String addClient(Client client ) throws EmailAlreadyUsedException {
        // test if email already used
        if (this.userRepository.existsByEmail(client.getEmail())) {

            throw new EmailAlreadyUsedException("Error: Email is already in use!");

        }else {

            client.setPassword(passwordEncoder.encode(client.getPassword()));

            // Traitement du Role client
            Set<Role> roles = new HashSet<>();
            Role clientRole = this.roleRepository.findByName(ERole.CLIENT)
                    .orElseThrow(() -> new ResourceNotFoundException("Error: Role is not found."));
            roles.add(clientRole);
            // Affect client Role
            client.setRoles(roles);
            //traitement du categorie

            Categorie categorieClient  = this.categorieRepository.findByName(ECategorie.BASIC)
                    .orElseThrow(() -> new ResourceNotFoundException("Error: Category  is not found."));
            client.setCategorie(categorieClient);


            this.clientRepository.save(client);
            return "Client registered successfully!";
        }
    }

    // Récupérer la liste des client


    public List<Client> getAllClients()
    {
        return this.clientRepository.findAll();
    }

    // Récupérer un client
    public Client findClientByID(Long id)
    {
        Optional<Client> clientData = this.clientRepository.findById(id);
        // Return statement if client exist or throw exception
        return clientData.orElseThrow(() -> new ResourceNotFoundException("client not found"));

    }


    // Modifier client account (api pour modification compte client)
    public String updateClientCompteByID(Long id, Client client) throws EmailAlreadyUsedException {
        String email = this.clientRepository.findById(id).get().getEmail();

        // test if email already used and not  client to modify email
        if (this.userRepository.existsByEmail(client.getEmail()) && !( email.equals(client.getEmail()))) {

             throw new EmailAlreadyUsedException("Error: Email is already in use!");

        }else {
            Optional<Client> clientData = this.clientRepository.findById(id);
            if (clientData.isPresent()) {
                Client existingClient = clientData.orElseThrow(() -> new ResourceNotFoundException("Client not found"));

                existingClient.setEmail(client.getEmail());
                // Change password if exist
                if(!client.getPassword().isEmpty())
                {
                    existingClient.setPassword(passwordEncoder.encode(client.getPassword()));
                }


                // save existingClient in the database
                this.clientRepository.save(existingClient);
                // return statement
                return " Client Account updated successfully!";
            } else {
                throw new ResourceNotFoundException("Client not found");
            }
        }

    }
    // Modifier client perso (api pour modification info perso client)
    public String updateClientPersoByID(Long id, Client client)
    {

            Optional<Client> clientData = this.clientRepository.findById(id);
            if (clientData.isPresent()) {
                Client existingClient = clientData.orElseThrow(() -> new ResourceNotFoundException("Client not found"));


                existingClient.setNom(client.getNom());
                existingClient.setPrenom(client.getPrenom());
                existingClient.setTelephone(client.getTelephone());
                existingClient.setSexe(client.getSexe());
                existingClient.setDate_naissance(client.getDate_naissance());




                // save existingClient in the database
                this.clientRepository.save(existingClient);
                // return statement
                return " Client  personal information updated successfully!";
            } else {
                throw new ResourceNotFoundException("Client not found");
            }
        }


    // Changer etat Client (activation et desactivation)
    public String changeEtatClientByID(Long id)
    {
        Optional<Client> clientData = this.clientRepository.findById(id);
        if (clientData.isPresent()) {
           Client existingClient = clientData.orElseThrow(() -> new ResourceNotFoundException("Client not found"));

            existingClient.setEtat(!existingClient.isEtat());

            // save existingClient in the database
            this.clientRepository.save(existingClient);
            // return statement
            return "Etat Client changed successfully!";
        } else {
            throw new ResourceNotFoundException("Client not found");
        }
    }




}
