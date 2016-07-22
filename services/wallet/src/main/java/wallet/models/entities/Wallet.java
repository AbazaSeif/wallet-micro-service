package wallet.models.entities;

import com.google.gson.GsonBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

@Entity
@Table(name = "wallet")
public class Wallet {
    @Id
    @Column(name = "id",nullable = false)
    @GeneratedValue
    private BigInteger id;

    @Column(name = "balance",nullable = false)
    @NotNull
    private BigDecimal balance;

    @Column(name = "creation_date_time",nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date creationDateTime;

    @Column(name = "fk_country",nullable = false)
    @NotNull
    private String fkCountry;

    @NotNull
    @OneToOne
    @JoinColumn(name = "fk_currency")
    private Currency fkCurrency;

    public Wallet() {
    }

    public Wallet(BigInteger id) {
        this.id = id;
    }

    public Wallet(BigDecimal balance, String fkCountry, Currency fkCurrency) {
        this.balance = balance;
        this.fkCountry = fkCountry;
        this.fkCurrency = fkCurrency;
        creationDateTime = new Date();
    }

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

    public Date getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(Date creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public String getFkCountry() {
        return fkCountry;
    }

    public void setFkCountry(String fkCountry) {
        this.fkCountry = fkCountry;
    }

    public Currency getFkCurrency() {
        return fkCurrency;
    }

    public void setFkCurrency(Currency fkCurrency) {
        this.fkCurrency = fkCurrency;
    }

    @Override
    public String toString(){
        return new GsonBuilder().create().toJson(this);
    }
}
