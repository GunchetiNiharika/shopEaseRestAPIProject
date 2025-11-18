package com.project.eCommerce.dto;

public class AuthRequest {

    private String name;
    private String email;
    private String password;

    // Default constructor
    public AuthRequest() {}

    // Constructor with parameters
    public AuthRequest(String name, String email, String password) {
        this.name =name;
        this.email = email;
        this.password = password;
    }

    // Getter for username
    public String getEmail() {
        return email;
    }

    // Setter for username
    public void setEmail(String email) {
        this.email = email;
    }

    // Getter for password
    public String getPassword() {
        return password;
    }

    // Setter for password
    public void setPassword(String password) {
        this.password = password;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
    @Override
    public String toString() {
        return "AuthRequest{" +
                "username='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
