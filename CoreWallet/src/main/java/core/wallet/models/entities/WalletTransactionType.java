package core.wallet.models.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by edgardneto on 6/22/16.
 */

@Entity
@Table(name = "wallet_transaction_type")
public class WalletTransactionType {

    @Id
    @Column(name = "id",unique = true,nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name",nullable = false)
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
