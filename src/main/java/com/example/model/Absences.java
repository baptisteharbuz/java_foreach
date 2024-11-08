package com.example.model;

import java.util.Date;

public class Absences {
    private int id;
    private Date dateDebut;
    private Date dateFin;
    private String type;
    private Etudiant etudiants;

    public Absences(int id, Date dateDebut, Date dateFin, String type, Etudiant etudiants) {
        this.id = id;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.type = type;
        this.etudiants = etudiants;
    }

    public Absences() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Etudiant getEtudiants() {
        return etudiants;
    }

    public void setEtudiants(Etudiant etudiants) {
        this.etudiants = etudiants;
    }
    
    
}
