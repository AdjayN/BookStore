package sr.unasat.BookStoreGem.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "purchases_books")
public class Purchases_book {


    @Column
    private int purchase_id;

    @Id
    @Column
    private int book_id;




    public Purchases_book(int purchase_id, int book_id) {
        this.purchase_id = purchase_id;
        this.book_id = book_id;
    }

    public Purchases_book() {

    }

    public int getPurchase_id() {
        return purchase_id;
    }

    public void setPurchase_id(int purchase_id) {
        this.purchase_id = purchase_id;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }



    @Override
    public String toString() {
        return "Purchases_book{" +
                "purchase_id=" + purchase_id +
                ", book_id=" + book_id +
                '}';
    }
}
