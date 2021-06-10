package com.bsofts.fidelity.payload.responses;


public class LoginResponse {

    private String token;

    private String type;

    private String message;
//getters & setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    //constructor

    public LoginResponse(String token, String type, String message) {
        this.token = token;
        this.type = type;
        this.message = message;
    }
}
