package com.bsofts.fidelity.models;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Categorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;
    @Enumerated(EnumType.STRING)
    @Column(length = 15)
    private ECategorie name ;
    private double montant_attribution;
    private int nombre_point_attribution;
    private double montant_retrait;
    private int nombre_point_retrait;
    // bi-directional many-to-one association to client
    @JsonIgnore
    @OneToMany(mappedBy = "categorie")
    private Set<Client> clients ;
      //getters setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ECategorie getName() {
        return name;
    }

    public void setName(ECategorie name) {
        this.name = name;
    }

    public double getMontant_attribution() {
        return montant_attribution;
    }

    public void setMontant_attribution(double montant_attribution) {
        this.montant_attribution = montant_attribution;
    }

    public int getNombre_point_attribution() {
        return nombre_point_attribution;
    }

    public void setNombre_point_attribution(int nombre_point_attribution) {
        this.nombre_point_attribution = nombre_point_attribution;
    }

    public double getMontant_retrait() {
        return montant_retrait;
    }

    public void setMontant_retrait(double montant_retrait) {
        this.montant_retrait = montant_retrait;
    }

    public int getNombre_point_retrait() {
        return nombre_point_retrait;
    }

    public void setNombre_point_retrait(int nombre_point_retrait) {
        this.nombre_point_retrait = nombre_point_retrait;
    }

    public Set<Client> getClients() {
        return clients;
    }

    public void setClients(Set<Client> clients) {
        this.clients = clients;
    }

    public Categorie(long id, ECategorie name, double montant_attribution, int nombre_point_attribution, double montant_retrait, int nombre_point_retrait, Set<Client> clients) {
        this.id = id;
        this.name = name;
        this.montant_attribution = montant_attribution;
        this.nombre_point_attribution = nombre_point_attribution;
        this.montant_retrait = montant_retrait;
        this.nombre_point_retrait = nombre_point_retrait;
        this.clients = clients;

    }

    public Categorie() {
    }
    
    

}
