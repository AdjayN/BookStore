package sr.unasat.BookStoreGem.Entities;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Entity
public class Purchases {
    @Id
    @GeneratedValue
    @Column (name = "purchase_id")
    private int idPurchase;

    @Column
    private String purchaseDate;

    @Column (name = "total_purhase_amount")
    private int totalPurchaseAmount;

    //Dit zijn References naar andere entities
    //Relatie tussen klanten
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "klanten_id_fk")
    private Klanten klanten;

    //Relatie tussen boeken
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "purchases_books",
            joinColumns = {@JoinColumn(name = "purchase_id")},
            inverseJoinColumns = {@JoinColumn(name = "book_id")}
    )
    private List<Books> booksList;

    //Relatie tussen Reservatie
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "reservering_id_fk")
    private Reserveringen reserveringen;


    public Purchases() {
        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date localDate = new Date();
        String localDateString = myFormat.format(localDate);
        this.purchaseDate = localDateString;
    }

    public int getIdPurchase() {
        return idPurchase;
    }

    public void setIdPurchase(int idPurchase) {
        this.idPurchase = idPurchase;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public int getTotalPurchaseAmount() {
        return totalPurchaseAmount;
    }

    public void setTotalPurchaseAmount(int totalPurchaseAmount) {
        this.totalPurchaseAmount = totalPurchaseAmount;
    }

    public Klanten getKlanten() {
        return klanten;
    }

    public void setKlanten(Klanten klanten) {this.klanten = klanten;
    }

    public List<Books> getBooksList() {
        return booksList;
    }

    public void setBooksList(List<Books> booksList) {
        this.booksList = booksList;
    }

    public Reserveringen getReserveringen() {
        return reserveringen;
    }

    public void setReserveringen(Reserveringen reserveringen) {
        this.reserveringen = reserveringen;
    }

    @Override
    public String toString() {
        return "Purchases{" +
                "idPurchase=" + idPurchase +
                ", purchaseDate='" + purchaseDate + '\'' +
                ", totalPurchaseAmount=" + totalPurchaseAmount +
                ", klanten=" + klanten +
                ", booksList=" + booksList +
                ", reserveringen=" + reserveringen +
                '}';
    }
}


