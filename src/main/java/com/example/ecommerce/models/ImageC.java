/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ecommerce.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author hp
 */
@Table(name="Image")
@Entity
public class ImageC{
     @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="nom")
    private String nom;
    @Column(name="url")
    private String url;
    @Column(name="type")
    private String type;
    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name="produit_id",nullable=true)
    private Produit produit;

    public ImageC() {
        this.nom="";
        this.url="";
        this.type="";
    }

    public ImageC(String nom, String url, String type) {
        this.nom = nom;
        this.url = url;
        this.type = type;
    }
    

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getUrl() {
        return url;
    }

    public String getType() {
        return type;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }
    
}
