package be.vdab.muziek.domain;


import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "albums")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "artiestid")
    Artiest artiest;

    @NotBlank
    String naam;

    @ElementCollection
    @CollectionTable(name = "tracks",
            joinColumns = @JoinColumn(name = "albumid"))
    private Set<Track> tracks= new LinkedHashSet<>();

   // int score;

    protected Album(){}
    public Album(Artiest artiest, String naam) {
        this.artiest = artiest;
        this.naam = naam;
     //   this.score = score;
    }

    public Set<Track> getTracks(){
        return Collections.unmodifiableSet(tracks);
    }

    public long getId() {
        return id;
    }

    public Artiest getArtiest() {
        return artiest;
    }

    public String getNaam() {
        return naam;
    }

    Album getAlbum(){
        return new Album(artiest,naam);
    }

}
