package sr.unasat.BookStoreGem.Entities;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Author {
    @Id
    @GeneratedValue
    @Column (name = "author_id")
    private int idAuthor;

    @Column
    private String naam;

    @Column
    private String achternaam;


    @ManyToMany(mappedBy = "authorList")
    private List<Books> booksList = new ArrayList<>();



    public Author(String naam, String achternaam) {
        this.naam = naam;
        this.achternaam = achternaam;
    }

    public Author() {
    }

    public int getIdAuthor() {
        return idAuthor;
    }

    public void setIdAuthor(int idAuthor) {
        this.idAuthor = idAuthor;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public List<Books> getBooksList() {
        return booksList;
    }

    public void setBooksList(List<Books> booksList) {
        this.booksList = booksList;
    }

    @Override
    public String toString() {
        return "Author{" +
                "idAuthor=" + idAuthor +
                ", naam='" + naam + '\'' +
                ", achternaam='" + achternaam + '\'' +
                '}';
    }
}
