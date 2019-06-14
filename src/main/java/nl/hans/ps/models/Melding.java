package nl.hans.ps.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "meldingen")
public class Melding {

    @Id
    private String melding_id;

    private String melding_tekst;

    public Melding() {

    }

    public Melding(String id, String tekst) {

        this.melding_id = id;
        this.melding_tekst = tekst;
    }

    public String getMelding_id() {

        return melding_id;
    }

    public void setMelding_id(String melding_id) {

        this.melding_id = melding_id;
    }

    public String getMelding_tekst() {

        return melding_tekst;
    }

    public void setMelding_tekst(String melding_tekst) {

        this.melding_tekst = melding_tekst;
    }

    @Override
    public String toString() {

        return "Melding{" +
                "melding_id='" + melding_id + '\'' +
                ", melding_tekst='" + melding_tekst + '\'' +
                '}';
    }

}
