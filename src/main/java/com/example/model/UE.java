package com.example.model;

public class UE {
    private int id;
    private String libelle;

    public UE(String libelle) {
        this.libelle = libelle;
    }
    
    public UE(int id, String libelle) {
        this.id = id;
        this.libelle = libelle;
    }

    public UE() {
    }

    public int getId() {
        return id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    
}
