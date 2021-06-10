package com.bsofts.fidelity.payload.requests;

import javax.validation.constraints.NotBlank;

public class LoginRequest {
    // Attribut avec controle de saisie non vide
    @NotBlank
    private String email;
    @NotBlank
    private String password;
     //getters & setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
     //constructor
    public LoginRequest(@NotBlank String email, @NotBlank String password) {
        this.email = email;
        this.password = password;
    }
}
