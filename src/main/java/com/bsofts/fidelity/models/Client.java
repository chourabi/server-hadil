package com.bsofts.fidelity.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.hibernate.envers.RelationTargetAuditMode;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Data
@Audited
public class Client  extends User {
    @NotAudited
    private String nom;
    @NotAudited
    private String prenom;
    @NotAudited
    private String telephone;
    @NotAudited
    private String sexe;
    private int nombre_points;
    @NotAudited
    private Date date_naissance;
    @NotAudited
    private int niveau;

    // bi-directional many-to-one association to category

    @ManyToOne
    @JoinColumn(name="categorie_id", nullable=false)
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private Categorie categorie  ;


    @NotAudited
    @JsonIgnore
    @OneToMany(mappedBy ="client")
    Set<TransactionAchat> transactionAchats;


    @NotAudited
    @JsonIgnore
    @OneToMany(mappedBy ="client")
    Set<BandeAchat> bandeAchats;
    


	@NotAudited
    @Column(name = "etat")
    //@Value("true")
    private boolean etat;

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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public int getNombre_points() {
        return nombre_points;
    }

    public void setNombre_points(int nombre_points) {
        this.nombre_points = nombre_points;
    }

    public Date getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(Date date_naissance) {
        this.date_naissance = date_naissance;
    }

    public boolean isEtat() {
        return etat;
    }

    public void setEtat(boolean etat) {
        this.etat = etat;
    }

	public int getNiveau() {
		return niveau;
	}

	public void setNiveau(int niveau) {
		this.niveau = niveau;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public Set<TransactionAchat> getTransactionAchats() {
		return transactionAchats;
	}

	public void setTransactionAchats(Set<TransactionAchat> transactionAchats) {
		this.transactionAchats = transactionAchats;
	}

	public Set<BandeAchat> getBandeAchats() {
		return bandeAchats;
	}

	public void setBandeAchats(Set<BandeAchat> bandeAchats) {
		this.bandeAchats = bandeAchats;
	}
    
}
