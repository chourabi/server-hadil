package com.bsofts.fidelity.services;


import com.bsofts.fidelity.exceptions.ResourceNotFoundException;
import com.bsofts.fidelity.models.Role;
import com.bsofts.fidelity.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;
    // Ajout role
    public Role saveNewRole(Role role)
    {
        return this.roleRepository.save(role);
    }

    // Récupérer tous les roles
    public List<Role> getAllRoles()
    {
        return this.roleRepository.findAll();
    }

    // Récupérer
    public Role findRoleByID(Long id)
    {
        Optional<Role> roleData = this.roleRepository.findById(id);
        // Return statement if user exist or null
        return roleData.orElseThrow(() -> new ResourceNotFoundException(
                "Role not found"));

    }
    public String updateRoleByID(Long id, Role role)
    {
        Optional<Role> roleData = this.roleRepository.findById(id);
        if (roleData.isPresent()) {
            Role existingRole = roleData.orElse(null);
            existingRole.setName(role.getName());
            // save existing User in the database
            this.roleRepository.save(existingRole);
            // return statement
            return "Role updated successfully!";
        } else {
            throw new ResourceNotFoundException("Role not found");
        }
    }

}
