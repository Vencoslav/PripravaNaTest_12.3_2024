import java.time.LocalDate;

public class Zamestnanec {
    private String jmeno;
    private String primeni;
    private Boolean pojisteni;
    private LocalDate datum;

    public Zamestnanec(String jmeno,String primeni, Boolean pojisteni, LocalDate datum){
        this.jmeno = jmeno;
        this.primeni = primeni;
        this.pojisteni = pojisteni;
        this.datum = datum;

    }
    public String getJmeno() {
        return jmeno;
    }

    public String getPrijmeni() {
        return primeni;
    }

    public Boolean getPojisteni() {
        return pojisteni;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setJmeno(String jmeno) {
        this.jmeno = jmeno;
    }

    public void setPrijmeni(String prijmeni) {
        this.primeni = prijmeni;
    }

    public void setPojisteni(Boolean pojisteni) {
        this.pojisteni = pojisteni;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }
}
