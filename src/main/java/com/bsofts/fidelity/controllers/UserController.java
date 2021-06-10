package com.bsofts.fidelity.controllers;

import com.bsofts.fidelity.models.User;
import com.bsofts.fidelity.payload.responses.MessageResponse;
import com.bsofts.fidelity.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    UserService userService;

    // Ajout utilisateur
    @PostMapping("/")
    public ResponseEntity<User> saveNewUser(@RequestBody User user)
    {
        User savedUser =  this.userService.saveNewUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    // Récupérer la liste des utilisateurs
    @GetMapping("/")
    public ResponseEntity<List<User>> getAllUsers()
    {
        List<User> listUsers = this.userService.getAllUsers();
        return new ResponseEntity<>(listUsers, HttpStatus.OK);
    }


    // Récupérer un utilisateur par son id
    @GetMapping("/{id}")
    public ResponseEntity<?> findUserByID(@PathVariable("id") Long id)
    {
        User user = this.userService.findUserByID(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // Récupérer un utilisateur par son id
    @GetMapping("/email/{email}")
    public ResponseEntity<?> findUserByEmail(@PathVariable("email") String email)
    {
        User user = this.userService.findUserByEmail(email);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // Modifier un utilisateur
    @PutMapping("/{id}")
    public ResponseEntity<MessageResponse> updateUserByID(@PathVariable("id") Long id, @RequestBody User user)
    {
        String message = this.userService.updateUserByID(id, user);
        return new ResponseEntity<>(new MessageResponse(message), HttpStatus.OK);
    }


    // Affecter un role a un utilisateur
    @PutMapping("/affect-role/{idUser}/{idRole}")
    public ResponseEntity<MessageResponse> affectUserToRole(@PathVariable(value="idUser") Long idUser, @PathVariable(value="idRole")Long idRole) {
        String message = this.userService.affectUserToRole(idUser, idRole);
        return new ResponseEntity<>(new MessageResponse(message), HttpStatus.OK);
    }
}
