package core.wallet.models.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by edgardneto on 6/22/16.
 */

@Entity
@Table(name = "wallet_transaction_type")
public class WalletTransactionType {

    @Id
    @Column(name = "id",unique = true,nullable = false)
    private String id;

    @Column(name = "creation_date_time",nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date creationDateTime;

    @Column(name = "additional_information")
    private String additional_information;

    public WalletTransactionType(String id) {
        this.id = id;
        creationDateTime = new Date();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(Date creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public String getAdditional_information() {
        return additional_information;
    }

    public void setAdditional_information(String additional_information) {
        this.additional_information = additional_information;
    }
}
