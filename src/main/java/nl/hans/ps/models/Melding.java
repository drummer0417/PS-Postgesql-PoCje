package nl.hans.ps.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "meldingen")
public class Melding {

    @Id
    @Column(name = "melding_id")
    private String meldingId;

    @Column(name = "melding_tekst")
    private String meldingTekst;

    public Melding() {

    }

    public Melding(String id, String tekst) {

        this.meldingId = id;
        this.meldingTekst = tekst;
    }

    public String getMeldingId() {

        return meldingId;
    }

    public void setMeldingId(String meldingId) {

        this.meldingId = meldingId;
    }

    public String getMeldingTekst() {

        return meldingTekst;
    }

    public void setMeldingTekst(String meldingTekst) {

        this.meldingTekst = meldingTekst;
    }

    @Override
    public String toString() {

        return "Melding{" +
                "meldingId='" + meldingId + '\'' +
                ", meldingTekst='" + meldingTekst + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Melding melding = (Melding) o;

        if (!meldingId.equals(melding.meldingId)) return false;
        return meldingTekst.equals(melding.meldingTekst);
    }

    @Override
    public int hashCode() {

        int result = meldingId.hashCode();
        result = 31 * result + meldingTekst.hashCode();
        return result;
    }
}
