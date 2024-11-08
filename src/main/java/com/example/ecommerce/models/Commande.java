
package com.example.ecommerce.models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Table(name="Commande")
@Entity
public class Commande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
   @ManyToOne(optional = false)
    @JoinColumn(name = "client_id", nullable = false)
    private User client;
   
   @OneToMany(mappedBy = "commandeLC", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
      private List<Ligne_Cmd> lignes= new ArrayList<>();
   
}

