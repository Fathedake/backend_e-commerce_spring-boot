/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ecommerce.models;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Table(name="Ligne_Cmd")
@Entity
public class Ligne_Cmd {

    @EmbeddedId
   private Ligne_CmdId id;

    @ManyToOne
    @MapsId("produitId")
    @JoinColumn(name = "produit_id")
   private Produit produit;

    @ManyToOne
    @MapsId("CommandeId")
    @JoinColumn(name = "commande_id")
   private Commande commandeLC;
     
    @Column(name="quantity")
    private int quantity;

    public Ligne_CmdId getId() {
        return id;
    }

    public Produit getProduit() {
        return produit;
    }

    public Commande getCommandeLC() {
        return commandeLC;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setId(Ligne_CmdId id) {
        this.id = id;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public void setCommandeLC(Commande commandeLC) {
        this.commandeLC = commandeLC;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
}
