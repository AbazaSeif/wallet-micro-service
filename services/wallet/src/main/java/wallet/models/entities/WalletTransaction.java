package wallet.models.entities;

import com.google.gson.GsonBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

@Entity
@Table(name = "wallet_transaction")
public class WalletTransaction {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;

    @NotNull
    @OneToOne
    @JoinColumn(name = "fk_wallet_source")
    private Wallet fkWalletSource;

    @NotNull
    @OneToOne
    @JoinColumn(name = "fk_wallet_dest")
    private Wallet fkWalletDest;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "reference", nullable = false)
    private String reference;

    @Column(name = "description")
    private String description;

    @Column(name = "creation_date_time", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date creation_date_time;

    @NotNull
    @OneToOne
    @JoinColumn(name = "fk_transaction_type")
    private WalletTransactionType fkTransactionType;

    @Column(name = "additional_information")
    private String additionalInformation;

    public WalletTransaction() {
        this.creation_date_time = new Date();
    }

    public WalletTransaction(Wallet fkWalletSource, Wallet fkWalletDest, BigDecimal amount, String reference, WalletTransactionType fkTransactionType) {
        this.fkWalletSource = fkWalletSource;
        this.fkWalletDest = fkWalletDest;
        this.amount = amount;
        this.reference = reference;
        this.fkTransactionType = fkTransactionType;
        this.creation_date_time = new Date();
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public Wallet getFkWalletSource() {
        return fkWalletSource;
    }

    public void setFkWalletSource(Wallet fkWalletSource) {
        this.fkWalletSource = fkWalletSource;
    }

    public Wallet getFkWalletDest() {
        return fkWalletDest;
    }

    public void setFkWalletDest(Wallet fkWalletDest) {
        this.fkWalletDest = fkWalletDest;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreation_date_time() {
        return creation_date_time;
    }

    public void setCreation_date_time(Date creation_date_time) {
        this.creation_date_time = creation_date_time;
    }

    public WalletTransactionType getFkTransactionType() {
        return fkTransactionType;
    }

    public void setFkTransactionType(WalletTransactionType fkTransactionType) {
        this.fkTransactionType = fkTransactionType;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    @Override
    public String toString(){
        return new GsonBuilder().create().toJson(this);
    }


}
