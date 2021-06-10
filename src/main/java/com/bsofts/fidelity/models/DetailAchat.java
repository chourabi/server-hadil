package com.bsofts.fidelity.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class DetailAchat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int nbrePoint;

    @Enumerated(EnumType.STRING)
    @Column(length = 15)
    private ModeAchat modeAchat;
    @ManyToOne
    @JoinColumn(name="transaction_id", nullable=false)
    private TransactionAchat transactionAchat ;

    @OneToOne
    @JoinColumn(name = "BandeAchat_id", referencedColumnName = "id",nullable=true)
    private BandeAchat bandeAchat ;

    public DetailAchat(int nbrePoint, ModeAchat modeAchat, TransactionAchat transactionAchat, BandeAchat bandeAchat) {
        this.nbrePoint = nbrePoint;
        this.modeAchat = modeAchat;
        this.transactionAchat = transactionAchat;
        this.bandeAchat = bandeAchat;
    }

    public DetailAchat(int nbrePoint, ModeAchat modeAchat, TransactionAchat transactionAchat) {
        this.nbrePoint = nbrePoint;
        this.modeAchat = modeAchat;
        this.transactionAchat = transactionAchat;
    }
}
