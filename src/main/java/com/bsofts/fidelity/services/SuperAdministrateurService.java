package com.bsofts.fidelity.services;

import com.bsofts.fidelity.exceptions.ResourceNotFoundException;
import com.bsofts.fidelity.models.ERole;
import com.bsofts.fidelity.models.Role;
import com.bsofts.fidelity.models.SuperAdministrateur;
import com.bsofts.fidelity.payload.responses.MessageResponse;
import com.bsofts.fidelity.repositories.RoleRepository;
import com.bsofts.fidelity.repositories.SuperAdministrateurRepository;
import com.bsofts.fidelity.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class SuperAdministrateurService {
    @Autowired
    SuperAdministrateurRepository superAdministrateurRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    // ajout SuperAdministrateur
    public String addSuperAdministrateur(SuperAdministrateur superAdministrateur) {
        // test if email already used
        if (this.userRepository.existsByEmail(superAdministrateur.getEmail())) {
            return "Email already in use";
            // throw new EmailAlreadyUsedException("Error: Email is already in use!");

        }else {

            superAdministrateur.setPassword(passwordEncoder.encode(superAdministrateur.getPassword()));

}
        // Traitement du Role administrateur
        Set<Role> roles = new HashSet<>();
        Role administrateurRole = this.roleRepository.findByName(ERole.SUPER_ADMIN)
                .orElseThrow(() -> new ResourceNotFoundException("Error: Role is not found."));
        roles.add(administrateurRole);
        // Affect superadministrateur Role
        superAdministrateur.setRoles(roles);
        this.superAdministrateurRepository.save(superAdministrateur);
        return "SuperAdministrateur registered successfully!";
    }
    // Récupérer la liste des superadministrateurs


    public List<SuperAdministrateur> getAllSuperAdministrateurs()
    {
        return this.superAdministrateurRepository.findAll();
    }

    // Récupérer un superAdministrateur
    public SuperAdministrateur findSuperAdministrateurByID(Long id)
    {
        Optional<SuperAdministrateur> superAdministrateurData = this.superAdministrateurRepository.findById(id);
        // Return statement if superadministrateur exist or throw exception
        return superAdministrateurData.orElseThrow(() -> new ResourceNotFoundException("SuperAdministrateur not found"));

    }
    // Modifier superAdministrateur (api pour modification administrateur)
    public String updateSuperAdministrateurByID(Long id, SuperAdministrateur superAdministrateur)
    {
        String email = this.superAdministrateurRepository.findById(id).get().getEmail();

        // test if email already used and not  superadministrateur to modify email
        if (this.userRepository.existsByEmail(superAdministrateur.getEmail()) && !( email.equals(superAdministrateur.getEmail()))) {
            return "Email already in use";
            // throw new EmailAlreadyUsedException("Error: Email is already in use!");

        }else {
            Optional<SuperAdministrateur> superAdministrateurData = this.superAdministrateurRepository.findById(id);
            if (superAdministrateurData.isPresent()) {
                SuperAdministrateur existingSuperAdministrateur = superAdministrateurData.orElseThrow(() -> new ResourceNotFoundException("SuperAdministrateur not found"));

                existingSuperAdministrateur.setEmail(superAdministrateur.getEmail());
                // Change password if exist
                if(!superAdministrateur.getPassword().isEmpty())
                {
                    existingSuperAdministrateur.setPassword(passwordEncoder.encode(superAdministrateur.getPassword()));
                }

                existingSuperAdministrateur.setNom(superAdministrateur.getNom());
                existingSuperAdministrateur.setPrenom(superAdministrateur.getPrenom());


                // save existingModerateur in the database
                this.superAdministrateurRepository.save(existingSuperAdministrateur);
                // return statement
                return "SuperAdministrateur updated successfully!";
            } else {
                throw new ResourceNotFoundException("SuperAdministrateur not found");
            }
        }

    }


    // Changer etat SuperAdministrateur (activation et desactivation)
    public String changeEtatSuperAdministrateurByID(Long id)
    {
        Optional<SuperAdministrateur> superAdministrateurData = this.superAdministrateurRepository.findById(id);
        if (superAdministrateurData.isPresent()) {
            SuperAdministrateur existingSuperAdministrateur = superAdministrateurData.orElseThrow(() -> new ResourceNotFoundException("SuperAdministrateur not found"));

            existingSuperAdministrateur.setEtat(!existingSuperAdministrateur.isEtat());

            // save existingsuperadministrateur in the database
            this.superAdministrateurRepository.save(existingSuperAdministrateur);
            // return statement
            return "Etat SuperAdministrateur changed successfully!";
        } else {
            throw new ResourceNotFoundException("SuperAdministrateur not found");
        }
    }



}
