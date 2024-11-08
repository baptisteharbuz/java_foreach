package com.example.model;

public class Suivre {
    private Etudiant etudiants;
    private Cours cours;

    public Suivre(Etudiant etudiants, Cours cours) {
        this.etudiants = etudiants;
        this.cours = cours;
    }

    public Suivre() {
    }

    public Etudiant getEtudiants() {
        return etudiants;
    }

    public void setEtudiants(Etudiant etudiants) {
        this.etudiants = etudiants;
    }

    public Cours getCours() {
        return cours;
    }

    public void setCours(Cours cours) {
        this.cours = cours;
    }

        
}