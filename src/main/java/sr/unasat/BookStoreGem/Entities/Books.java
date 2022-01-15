package sr.unasat.BookStoreGem.Entities;



import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Books {
    @Id
    @GeneratedValue
    @Column (name = "book_id")
    private int idbook;

    @Column
    private String isbn;

    @Column
    private String titel;

    @Column
    private int prijs;

    @Column
    private int aantal;

    @Column
    private int gereserveerd_aantal;

    //realtie met reserveringen
    @ManyToMany (mappedBy = "booksList")
    private List<Reserveringen> reserveringenList = new ArrayList<>();

    //Relatie met purchases
    @ManyToMany(mappedBy = "booksList")
    private List<Purchases> purchasesList = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "book_author",
            joinColumns = {@JoinColumn(name = "book_id")},
            inverseJoinColumns = {@JoinColumn(name = "author_id")}
    )
    private List<Author> authorList = new ArrayList<>();

    public Books(String isbn, String titel, int prijs, int aantal) {
        this.isbn = isbn;
        this.titel = titel;
        this.prijs = prijs;
        this.aantal = aantal;
    }

    public Books() {
    }

    public int getIdbook() {
        return idbook;
    }

    public void setIdbook(int idbook) {
        this.idbook = idbook;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
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

    public int getPrijs() {
        return prijs;
    }

    public void setPrijs(int prijs) {
        this.prijs = prijs;
    }

    public int getAantal() {
        return aantal;
    }

    public void setAantal(int aantal) {
        this.aantal = aantal;
    }

    public int getGereserveerd_aantal() {
        return gereserveerd_aantal;
    }

    public void setGereserveerd_aantal(int gereserveerd_aantal) {
        this.gereserveerd_aantal = gereserveerd_aantal;
    }

    public List<Author> getAuthorList() {
        return authorList;
    }


    public void setAuthorList(List<Author> authorList) {
        this.authorList = authorList;
    }


    @Override
    public String toString() {
        return "Books{" +
                "idbook=" + idbook +
                ", isbn='" + isbn + '\'' +
                ", titel='" + titel + '\'' +
                ", prijs=" + prijs +
                ", aantal=" + aantal +
                ", gereserveerd_aantal=" + gereserveerd_aantal +
                ", authorList=" + authorList +
                '}';
    }
}
