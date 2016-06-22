package core.wallet.models.entities;

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
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Wallet.class)
    @JoinColumn(name = "fk_wallet_source", referencedColumnName = "id")
    private Wallet fk_wallet_source;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY,targetEntity = Wallet.class)
    @JoinColumn(name = "fk_wallet_dest",referencedColumnName = "id")
    private Wallet fk_wallet_dest;

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
    @ManyToOne(fetch = FetchType.LAZY,targetEntity = WalletTransactionType.class)
    @JoinColumn(name = "fk_transaction_type",referencedColumnName = "id")
    private WalletTransactionType fk_transaction_type;

    @Column(name = "additional_information")
    private String additional_information;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public Wallet getFk_wallet_source() {
        return fk_wallet_source;
    }

    public void setFk_wallet_source(Wallet fk_wallet_source) {
        this.fk_wallet_source = fk_wallet_source;
    }

    public Wallet getFk_wallet_dest() {
        return fk_wallet_dest;
    }

    public void setFk_wallet_dest(Wallet fk_wallet_dest) {
        this.fk_wallet_dest = fk_wallet_dest;
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

    public WalletTransactionType getFk_transaction_type() {
        return fk_transaction_type;
    }

    public void setFk_transaction_type(WalletTransactionType fk_transaction_type) {
        this.fk_transaction_type = fk_transaction_type;
    }

    public String getAdditional_information() {
        return additional_information;
    }

    public void setAdditional_information(String additional_information) {
        this.additional_information = additional_information;
    }
}
