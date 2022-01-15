package sr.unasat.BookStoreGem.Entities;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Klanten {
    @Id
    @GeneratedValue
    @Column (name = "klanten_id")
    private int idKlanten;

    @Column
    private String naam;

    @Column
    private String emailaddres;

    //klanten is the inverse end of the relation(relatie tussen reserveringen

    @OneToMany(mappedBy = "klanten",cascade = {CascadeType.MERGE})
    private List<Reserveringen> reserveringenList = new ArrayList<>();

    //Relatie met purchases
    @OneToMany(mappedBy = "klanten",cascade = {CascadeType.MERGE})
    private List<Purchases> purchasesList = new ArrayList<>();


    public Klanten(String naam, String emailaddres) {
        this.naam = naam;
        this.emailaddres = emailaddres;
    }

    public Klanten() {

    }

    public int getIdKlanten() {
        return idKlanten;
    }

    public void setIdKlanten(int idKlanten) {
        this.idKlanten = idKlanten;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getEmailaddres() {
        return emailaddres;
    }

    public void setEmailaddres(String emailaddres) {
        this.emailaddres = emailaddres;
    }

    public List<Reserveringen> getReserveringenList() {
        return reserveringenList;
    }

    public void setReserveringenList(List<Reserveringen> reserveringenList) {
        this.reserveringenList = reserveringenList;
    }

    public List<Purchases> getPurchasesList() {
        return purchasesList;
    }

    public void setPurchasesList(List<Purchases> purchasesList) {
        this.purchasesList = purchasesList;
    }

    @Override
    public String toString() {
        return "Klanten{" +
                "idKlanten=" + idKlanten +
                ", naam='" + naam + '\'' +
                ", emailaddres='" + emailaddres + '\'' +
                '}';
    }
}
