package com.bsofts.fidelity.services;

import com.bsofts.fidelity.domain.Mail;
import com.bsofts.fidelity.exceptions.EmailAlreadyUsedException;
import com.bsofts.fidelity.exceptions.ResourceNotFoundException;
import com.bsofts.fidelity.models.Administrateur;
import com.bsofts.fidelity.models.ERole;
import com.bsofts.fidelity.models.Manager;
import com.bsofts.fidelity.models.Role;
import com.bsofts.fidelity.repositories.ManagerRepository;
import com.bsofts.fidelity.repositories.RoleRepository;
import com.bsofts.fidelity.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ManagerService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    ManagerRepository managerRepository;
    @Autowired
    SendMailService sendMailService;


    // ajout manager
    public String addmanager (Manager manager) throws EmailAlreadyUsedException {
        // test if email already used
        if (this.userRepository.existsByEmail(manager.getEmail())) {

             throw new EmailAlreadyUsedException("Error: Email is already in use!");

        }else {
            String password= UUID.randomUUID().toString();
            manager.setPassword(password);

            manager.setPassword(passwordEncoder.encode(manager.getPassword()));
            sendMailService.sendMail(new Mail(manager.getEmail(),"inscription", "Bienvenue ,Vous avez un compte ,votre username est:  " +manager.getEmail()+" ,  votre password :  "+password));

            // Traitement du Role manager
            Set<Role> roles = new HashSet<>();
            Role managerRole = this.roleRepository.findByName(ERole.MANAGER)
                    .orElseThrow(() -> new ResourceNotFoundException("Error: Role is not found."));
            roles.add(managerRole);
            // Affect manager Role
            manager.setRoles(roles);
            this.managerRepository.save(manager);
            return "manager registered successfully!";
        }
    }

    // Récupérer la liste des managers


    public List<Manager> getAllManager()
    {
        return this.managerRepository.findAll();
    }

    // Récupérer un manager
    public Manager findManagerByID(Long id)
    {
        Optional<Manager> managerData = this.managerRepository.findById(id);
        // Return statement if manager exist or throw exception
        return managerData.orElseThrow(() -> new ResourceNotFoundException("manager not found"));

    }
    // modifier donnée personelles manager
    public String updateManagerDataByEmail(String email, Manager manager)
    {
        Optional<Manager> managerData = this.managerRepository.findByEmail(email);
        if (managerData.isPresent()) {
            Manager existingManager = managerData.orElseThrow(() -> new ResourceNotFoundException("manager not found"));

            existingManager.setNom(manager.getNom());
            existingManager.setPrenom(manager.getPrenom());

            // save existingCandidat in the database
            this.managerRepository.save(existingManager);
            // return statement
            return " manager updated successfully!";
        } else {
            throw new ResourceNotFoundException("manager not found");
        }
    }
    // Modifier data confid manager
    public String updateManagerDataConfidByEmail(String email, Manager manager)
    {
        // test if email already used and not current user email
        if (this.userRepository.existsByEmail(manager.getEmail()) && !( email.equals(manager.getEmail()))) {
            return "Email already in use";
            // throw new EmailAlreadyUsedException("Error: Email is already in use!");

        }else {
            Optional<Manager> managerData = this.managerRepository.findByEmail(email);
            if (managerData.isPresent()) {
                Manager existingManager = managerData.orElseThrow(() -> new ResourceNotFoundException("Manager not found"));

                existingManager.setEmail(manager.getEmail());
                // Change password if exist
                if(!manager.getPassword().isEmpty())
                {
                    existingManager.setPassword(passwordEncoder.encode(manager.getPassword()));
                }

                // save existingCandidat in the database
                this.managerRepository.save(existingManager);
                // return statement
                return "manager updated successfully!";
            } else {
                throw new ResourceNotFoundException("manager not found");
            }
        }
    }


    //modif manager par super admin
    public String updateManagerByID(Long id, Manager manager) throws EmailAlreadyUsedException {
        String email = this.managerRepository.findById(id).get().getEmail();

        // test if email already used and not  administrateur to modify email
        if (this.userRepository.existsByEmail(manager.getEmail()) && !( email.equals(manager.getEmail()))) {

             throw new EmailAlreadyUsedException("Error: Email is already in use!");

        }else {
            Optional<Manager> managerData = this.managerRepository.findById(id);
            if (managerData.isPresent()) {
                Manager existingManager = managerData.orElseThrow(() -> new ResourceNotFoundException("Administrateur not found"));

                existingManager .setEmail(manager.getEmail());

                existingManager.setNom(manager.getNom());
                existingManager.setPrenom(manager.getPrenom());


                // save existingModerateur in the database
                this.managerRepository.save(existingManager);
                // return statement
                return "manager updated successfully!";
            } else {
                throw new ResourceNotFoundException("manager not found");
            }
        }

    }
    //findmanager by email
    public Manager findManagerByEmail(String email)
    {
        Optional<Manager> managerData = this.managerRepository.findByEmail(email);
        // Return statement if candidat exist or throw exception
        return managerData.orElseThrow(() -> new ResourceNotFoundException("manager not found"));
    }


    // Changer etat manager (activation et desactivation)
    public String changeEtatManagerByID(Long id)
    {
        Optional<Manager> managerData = this.managerRepository.findById(id);
        if (managerData.isPresent()) {
            Manager existingManager = managerData.orElseThrow(() -> new ResourceNotFoundException("Manager not found"));

            existingManager.setEtat(!existingManager.isEtat());

            // save existingManager in the database
            this.managerRepository.save(existingManager);
            // return statement
            return "Etat Manager changed successfully!";
        } else {
            throw new ResourceNotFoundException("Manager not found");
        }
    }

}
