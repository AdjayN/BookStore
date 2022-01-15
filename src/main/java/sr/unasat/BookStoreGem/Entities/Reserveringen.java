package sr.unasat.BookStoreGem.Entities;

import javax.persistence.*;
import java.util.List;

@Entity

public class Reserveringen {
    @Id
    @GeneratedValue
    @Column (name ="reservering_id")
    private int idReservatie;

    @Column (name ="datum")
    private String reservatieDatum;

    @Column
    private int total_amount;

    //reservation is the owner of the relationship(Realtie tussen klanten)
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "klanten_id_fk")
    private Klanten klanten;

    //Relatie tussen books
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "reserveringen_books",
            joinColumns =  {@JoinColumn(name = "reservering_id")},
            inverseJoinColumns = {@JoinColumn(name = "books_id")}
    )
    private List<Books> booksList;

    //Relatie tussen purchases
    @OneToOne(mappedBy = "reserveringen")
    private Purchases purchases;

    public Reserveringen(String reservatieDatum) {
        this.reservatieDatum = reservatieDatum;
    }

    public Reserveringen() {
    }

    public int getIdReservatie() {
        return idReservatie;
    }

    public void setIdReservatie(int idReservatie) {
        this.idReservatie = idReservatie;
    }

    public String getReservatieDatum() {
        return reservatieDatum;
    }

    public void setReservatieDatum(String reservatieDatum) {
        this.reservatieDatum = reservatieDatum;
    }

    public Klanten getKlanten() {
        return klanten;
    }

    public void setKlanten(Klanten klanten) {
        this.klanten = klanten;
    }

    public List<Books> getBooksList() {
        return booksList;
    }

    public void setBooksList(List<Books> booksList) {
        this.booksList = booksList;
    }

    public Purchases getPurchases() {
        return purchases;
    }

    public void setPurchases(Purchases purchases) {
        this.purchases = purchases;
    }

    public int getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(int total_amount) {
        this.total_amount = total_amount;
    }

    @Override
    public String toString() {
        return "Reserveringen{" +
                "idReservatie=" + idReservatie +
                ", reservatieDatum='" + reservatieDatum + '\'' +
                ", total_amount=" + total_amount +
                ", klanten=" + klanten +
                ", books=" + booksList +
                '}';
    }
}



