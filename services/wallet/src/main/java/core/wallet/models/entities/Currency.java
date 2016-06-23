package core.wallet.models.entities;

import com.google.gson.GsonBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "currency")
public class Currency {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "creation_date_time", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date creation_date_time;

    public Currency() {
    }

    public Currency(String id) {
        this.id = id;
        this.creation_date_time = new Date();
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreation_date_time() {
        return creation_date_time;
    }

    public void setCreation_date_time(Date creation_date_time) {
        this.creation_date_time = creation_date_time;
    }

    @Override
    public String toString(){
        return new GsonBuilder().create().toJson(this);
    }

}
