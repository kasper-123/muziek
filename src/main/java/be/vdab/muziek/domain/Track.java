package be.vdab.muziek.domain;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Time;
import java.util.Locale;
import java.util.Objects;

@Embeddable
@Access(AccessType.FIELD)
public class Track {

    String naam;

    Time tijd;

    public String getNaam() {
        return naam;
    }

    public Time getTijd() {
        return tijd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o instanceof Track) {
            Track track = (Track) o;
            return naam.equalsIgnoreCase(track.getNaam());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return naam.toUpperCase().hashCode();
    }
}
