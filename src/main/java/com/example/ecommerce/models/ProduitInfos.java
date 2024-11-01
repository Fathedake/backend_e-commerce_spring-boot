/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ecommerce.models;

import java.util.Date;

public class ProduitInfos {
    private String nom;
    private String description;
    private double prix;
     private int quantite;
     private Date date_dep;
  

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public void setDate_dep(Date date_dep) {
        this.date_dep = date_dep;
    }
    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }

    public double getPrix() {
        return prix;
    }

    public int getQuantite() {
        return quantite;
    }

    public Date getDate_dep() {
        return date_dep;
    }
}
