package com.bsofts.fidelity.services;


import com.bsofts.fidelity.domain.Mail;
import com.bsofts.fidelity.exceptions.EmailAlreadyUsedException;
import com.bsofts.fidelity.exceptions.ResourceNotFoundException;
import com.bsofts.fidelity.models.*;
import com.bsofts.fidelity.repositories.AdministrateurRepository;
import com.bsofts.fidelity.repositories.ClientRepository;
import com.bsofts.fidelity.repositories.RoleRepository;
import com.bsofts.fidelity.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AdministrateurService {
    @Autowired
    AdministrateurRepository administrateurRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    SendMailService sendMailService;


    // ajout administrateur
    public String addAdministrateur (Administrateur administrateur) throws EmailAlreadyUsedException {
        // test if email already used
        if (this.userRepository.existsByEmail(administrateur.getEmail())) {

             throw new EmailAlreadyUsedException("Error: Email is already in use!");

        }else {
                String password= UUID.randomUUID().toString();
                administrateur.setPassword(password);
                administrateur.setPassword(passwordEncoder.encode(administrateur.getPassword()));
            sendMailService.sendMail(new Mail(administrateur.getEmail(),"inscription", "Bienvenue ,Vous avez un compte ,votre username est:  " +administrateur.getEmail()+" ,  votre password :  "+password));

            // Traitement du Role administrateur
            Set<Role> roles = new HashSet<>();
            Role administrateurRole = this.roleRepository.findByName(ERole.ADMIN)
                    .orElseThrow(() -> new ResourceNotFoundException("Error: Role is not found."));
            roles.add(administrateurRole);
            // Affect administrateur Role
            administrateur.setRoles(roles);
            this.administrateurRepository.save(administrateur);
            return "administrateur registered successfully!";
        }
    }

    // Récupérer la liste des administrateurs


    public List<Administrateur> getAllAdministrateurs()
    {
        return this.administrateurRepository.findAll();
    }

    // Récupérer un administrateur
    public Administrateur findAdministrateurByID(Long id)
    {
        Optional<Administrateur> administrateurData = this.administrateurRepository.findById(id);
        // Return statement if administrateur exist or throw exception
        return administrateurData.orElseThrow(() -> new ResourceNotFoundException("administrateur not found"));

    }

   // find admin by email
   public Administrateur findAdministrateurByEmail(String email)
   {
       Optional<Administrateur> adminData = this.administrateurRepository.findByEmail(email);
       // Return statement if candidat exist or throw exception
       return adminData.orElseThrow(() -> new ResourceNotFoundException("administrateur not found"));
   }



    // Modifier administrateur account (api pour modification compte administrateur)
    public String updateAdministrateurCompteByID(Long id, Administrateur administrateur) throws EmailAlreadyUsedException {
        String email = this.administrateurRepository.findById(id).get().getEmail();

        // test if email already used and not  administrateur to modify email
        if (this.userRepository.existsByEmail(administrateur.getEmail()) && !( email.equals(administrateur.getEmail()))) {

            throw new EmailAlreadyUsedException("Error: Email is already in use!");

        }else {
            Optional<Administrateur> administrateurData = this.administrateurRepository.findById(id);
            if (administrateurData.isPresent()) {
                Administrateur existingAdministrateur = administrateurData.orElseThrow(() -> new ResourceNotFoundException("Administrateur not found"));

                existingAdministrateur.setEmail(administrateur.getEmail());
                // Change password if exist
                if(!administrateur.getPassword().isEmpty())
                {
                    existingAdministrateur.setPassword(passwordEncoder.encode(administrateur.getPassword()));
                }

                // save existingadministrateur in the database
                this.administrateurRepository.save(existingAdministrateur);
                // return statement
                return " Administrateur Account updated successfully!";
            } else {
                throw new ResourceNotFoundException("Administrateur not found");
            }
        }

    }
    // Modifier Administrateur perso (api pour modification info perso client)
    public String updateAdministrateurPersoByID(Long id, Administrateur administrateur)
    {

        Optional<Administrateur> administrateurData = this.administrateurRepository.findById(id);
        if (administrateurData.isPresent()) {
            Administrateur existingClient = administrateurData.orElseThrow(() -> new ResourceNotFoundException("Administrateur not found"));


            existingClient.setNom(administrateur.getNom());
            existingClient.setPrenom(administrateur.getPrenom());






            // save existingClient in the database
            this.administrateurRepository.save(existingClient);
            // return statement
            return " Administrateur  personal information updated successfully!";
        } else {
            throw new ResourceNotFoundException("Administrateur not found");
        }
    }

    //edit admin par super admin
    public String updateAdministrateurByID(Long id, Administrateur administrateur) throws EmailAlreadyUsedException {
        String email = this.administrateurRepository.findById(id).get().getEmail();

        // test if email already used and not  administrateur to modify email
        if (this.userRepository.existsByEmail(administrateur.getEmail()) && !( email.equals(administrateur.getEmail()))) {

             throw new EmailAlreadyUsedException("Error: Email is already in use!");

        }else {
            Optional<Administrateur> moderateurData = this.administrateurRepository.findById(id);
            if (moderateurData.isPresent()) {
                Administrateur existingAdministrateur = moderateurData.orElseThrow(() -> new ResourceNotFoundException("Administrateur not found"));

                existingAdministrateur.setEmail(administrateur.getEmail());



                existingAdministrateur.setNom(administrateur.getNom());
                existingAdministrateur.setPrenom(administrateur.getPrenom());


                // save existingModerateur in the database
                this.administrateurRepository.save(existingAdministrateur);
                // return statement
                return "Administrateur updated successfully!";
            } else {
                throw new ResourceNotFoundException("Administrateur not found");
            }
        }

    }
    // update admin by email
    // Modifier data confid Candidat
    public String updateAdministrateurCompteByEmail(String email, Administrateur administrateur) throws EmailAlreadyUsedException {
        // test if email already used and not current user email
        if (this.userRepository.existsByEmail(administrateur.getEmail()) && !(email.equals(administrateur.getEmail()))) {

            throw new EmailAlreadyUsedException("Error: Email is already in use!");

        } else {
            Optional<Administrateur> adminData = this.administrateurRepository.findByEmail(email);
            if (adminData.isPresent()) {
                Administrateur existingAdministrateur = adminData.orElseThrow(() -> new ResourceNotFoundException("administrateur not found"));

                existingAdministrateur.setEmail(administrateur.getEmail());
                // Change password if exist
                if (!administrateur.getPassword().isEmpty()) {
                    existingAdministrateur.setPassword(passwordEncoder.encode(administrateur.getPassword()));
                }
                existingAdministrateur.setNom(administrateur.getNom());
                existingAdministrateur.setPrenom(administrateur.getPrenom());
                // save existingCandidat in the database
                this.administrateurRepository.save(existingAdministrateur);
                // return statement
                return "administrateur updated successfully!";
            } else {
                throw new ResourceNotFoundException("adminstrateur not found");
            }
        }

    }

    // Changer etat Administrateur (activation et desactivation)
    public String changeEtatAdministrateurByID(Long id)
    {
        Optional<Administrateur> administrateurData = this.administrateurRepository.findById(id);
        if (administrateurData.isPresent()) {
            Administrateur existingAdministrateur = administrateurData.orElseThrow(() -> new ResourceNotFoundException("Client not found"));

            existingAdministrateur.setEtat(!existingAdministrateur.isEtat());

            // save existingClient in the database
            this.administrateurRepository.save(existingAdministrateur);
            // return statement
            return "Etat administrateur changed successfully!";
        } else {
            throw new ResourceNotFoundException("Administrateur not found");
        }
    }




}
