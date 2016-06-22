package core.wallet.models.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

@Entity
@Table(name = "wallet")
public class Wallet {
    @Id
    @Column(name = "id",unique = true,nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;

    @Column(name = "balance",nullable = false)
    private BigDecimal balance;

    @Column(name = "creation_date_time",nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date creation_date_time;

    @Column(name = "fk_country",nullable = false)
    private String fk_country;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY,targetEntity = Currency.class)
    @JoinColumn(name = "fk_currency",referencedColumnName = "id")
    private Currency fk_currency;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Date getCreation_date_time() {
        return creation_date_time;
    }

    public void setCreation_date_time(Date creation_date_time) {
        this.creation_date_time = creation_date_time;
    }

    public String getFk_country() {
        return fk_country;
    }

    public void setFk_country(String fk_country) {
        this.fk_country = fk_country;
    }

    public Currency getFk_currency() {
        return fk_currency;
    }

    public void setFk_currency(Currency fk_currency) {
        this.fk_currency = fk_currency;
    }
}
