package com.example.tpsqlite.classes;

import java.util.Date;

public class Etudiant {
    private int id;
    private String nom;
    private String prenom;

    private String dateNaissance;

    public Etudiant(String nom, String prenom, String dateNaissance) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
    }

    public Etudiant() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getDateNaissance() { return dateNaissance; }

    public void setDateNaissance(String dateNaissance) { this.dateNaissance = dateNaissance; }


}
