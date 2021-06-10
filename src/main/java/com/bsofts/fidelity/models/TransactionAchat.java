package com.bsofts.fidelity.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class TransactionAchat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date date ;
    private double montantAchat;
    @ManyToOne
    @JoinColumn(name="id_client")
    private Client client;

    @JsonIgnore
    @OneToMany(mappedBy ="transactionAchat")
    Set<DetailAchat> detailAchats;

    public TransactionAchat(Date date, double montantAchat, Client client) {
        this.date = date;
        this.montantAchat = montantAchat;
        this.client = client;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getMontantAchat() {
		return montantAchat;
	}

	public void setMontantAchat(double montantAchat) {
		this.montantAchat = montantAchat;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Set<DetailAchat> getDetailAchats() {
		return detailAchats;
	}

	public void setDetailAchats(Set<DetailAchat> detailAchats) {
		this.detailAchats = detailAchats;
	}
    
    
}
