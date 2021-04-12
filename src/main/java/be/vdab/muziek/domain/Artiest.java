package be.vdab.muziek.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "artiesten")
public class Artiest {

    @Id
    private  long id;

    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    @NotBlank
    private String naam;
}
