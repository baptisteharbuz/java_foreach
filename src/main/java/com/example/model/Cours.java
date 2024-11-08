package com.example.model;

import java.util.Date;
import java.util.List;

public class Cours {
    private int id;
    private Date dateDebut;
    private Date dateFin;
    private UE ue;
    private Formateur formateur;
    private List<Etudiant> etudiants;

    public Cours(int id, Date dateDebut, Date dateFin, UE ue, Formateur formateur) {
        this.id = id;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.ue = ue;
        this.formateur = formateur;
    }

    public Cours(int id, Date dateDebut, Date dateFin, UE ue, Formateur formateur,List<Etudiant> etudiants) {
        this.id = id;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.ue = ue;
        this.formateur = formateur;
        this.etudiants = etudiants;
    }

    public Cours() {
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

    public UE getUe() {
        return ue;
    }

    public void setUe(UE ue) {
        this.ue = ue;
    }

    public Formateur getFormateur() {
        return formateur;
    }

    public void setFormateur(Formateur formateur) {
        this.formateur = formateur;
    }

    public List<Etudiant> getEtudiants() {
        return etudiants;
    }

    public void setEtudiants(List<Etudiant> etudiants) {
        this.etudiants = etudiants;
    }
 
    
    
}
