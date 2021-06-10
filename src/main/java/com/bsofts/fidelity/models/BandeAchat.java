package com.bsofts.fidelity.models;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class BandeAchat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;
    @Enumerated(EnumType.STRING)
    @Column(length = 15)
    private EBandeAchat etat ;
    private int  code;
    private LocalDateTime dateExpiration;
    private double montantBandeAchat;
    private LocalDateTime dateCreation ;
    @ManyToOne
    @JoinColumn(name="client_id", nullable=false)
    private Client client  ;


    @OneToOne(mappedBy = "bandeAchat")
    private DetailAchat detailAchat;



    public BandeAchat(EBandeAchat etat, int code, LocalDateTime dateExpiration, double montantBandeAchat, LocalDateTime dateCreation, Client client) {
        this.etat = etat;
        this.code = code;
        this.dateExpiration = dateExpiration;
        this.montantBandeAchat = montantBandeAchat;
        this.dateCreation = dateCreation;
        this.client = client;
    }



	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public EBandeAchat getEtat() {
		return etat;
	}



	public void setEtat(EBandeAchat etat) {
		this.etat = etat;
	}



	public int getCode() {
		return code;
	}



	public void setCode(int code) {
		this.code = code;
	}



	public LocalDateTime getDateExpiration() {
		return dateExpiration;
	}



	public void setDateExpiration(LocalDateTime dateExpiration) {
		this.dateExpiration = dateExpiration;
	}



	public double getMontantBandeAchat() {
		return montantBandeAchat;
	}



	public void setMontantBandeAchat(double montantBandeAchat) {
		this.montantBandeAchat = montantBandeAchat;
	}



	public LocalDateTime getDateCreation() {
		return dateCreation;
	}



	public void setDateCreation(LocalDateTime dateCreation) {
		this.dateCreation = dateCreation;
	}



	public Client getClient() {
		return client;
	}



	public void setClient(Client client) {
		this.client = client;
	}



	public DetailAchat getDetailAchat() {
		return detailAchat;
	}



	public void setDetailAchat(DetailAchat detailAchat) {
		this.detailAchat = detailAchat;
	}



	public BandeAchat() {
		super();
	}
    
	
	
    
}
