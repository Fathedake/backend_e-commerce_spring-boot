/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ecommerce.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author hp
 */
@Embeddable
public class Ligne_CmdId implements Serializable{
     @Column(name = "produit_id")
    Long produitId;
    @Column(name = "commande_id")
    Long commandeId;
}
