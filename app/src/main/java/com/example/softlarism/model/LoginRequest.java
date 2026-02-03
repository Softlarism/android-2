package com.example.softlarism.model;

public class LoginRequest {
    private String telefono;
    private String contrasena;

    public LoginRequest(String telefono, String contrasena) {
        this.telefono = telefono;
        this.contrasena = contrasena;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}
