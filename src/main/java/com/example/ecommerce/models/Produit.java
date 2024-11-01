/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ecommerce.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "produit")
public class Produit{

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @Column(name="nom")
    private String nom;
    @Column(name="description")
    private String description;
     @Column(name="prix")
    private double prix;
     @Column(name="quantite")
     private int quantite;
      @Column(name="date_dep")
     private Date date_dep;
    @ManyToOne()
    @JoinColumn(name = "categorie_id", nullable = false)
    private Categorie categorie;
 
      @OneToMany(mappedBy = "produit",targetEntity=ImageC.class)
   private List<ImageC> images;
      @OneToMany(mappedBy = "produit",targetEntity=Ligne_Cmd.class)
   private List<Ligne_Cmd> lignes ;
     
     

    public List<ImageC> getImages() {
        return images;
    }

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

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
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

    public Categorie getCategorie() {
        return categorie;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

} 
