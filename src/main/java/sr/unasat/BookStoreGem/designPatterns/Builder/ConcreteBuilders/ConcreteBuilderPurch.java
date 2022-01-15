package sr.unasat.BookStoreGem.designPatterns.Builder.ConcreteBuilders;


import sr.unasat.BookStoreGem.DAO.BooksDAO;
import sr.unasat.BookStoreGem.Entities.Books;
import sr.unasat.BookStoreGem.Entities.Klanten;
import sr.unasat.BookStoreGem.Entities.Purchases;
import sr.unasat.BookStoreGem.Entities.Reserveringen;
import sr.unasat.BookStoreGem.config.JPAConfiguration;
import sr.unasat.BookStoreGem.designPatterns.Builder.Interfaces.BuilderPurch;

import java.util.ArrayList;
import java.util.List;

public class ConcreteBuilderPurch implements BuilderPurch {

    BooksDAO booksDAO = new BooksDAO(JPAConfiguration.getEntityManager());

    private String purchaseD;
    private Klanten klant;
    private List<Books> boeklist;
    private Reserveringen reservering;
    private int totalAmountPurch;



    @Override
    public BuilderPurch withPurchaseDatum(String purchaseDatum) {
        purchaseD = purchaseDatum;
        return this;
    }

    @Override
    public BuilderPurch withKlanten(Klanten klanten) {
        klant = klanten;
        return this;
    }

    @Override
    public BuilderPurch withBooks(List<Books> booksList) {

        List<Books> booksListUpdatAantal = new ArrayList<>();

        if(booksList.size() == 1){
            Books book1 = booksList.get(0);
            int book1Id = book1.getIdbook();
            Books book1UpdateAantal = booksDAO.updateBooksAantal(book1Id);
            booksListUpdatAantal.add(book1UpdateAantal);
        }if(booksList.size() == 2){
            Books book1 = booksList.get(0);
            Books book2 = booksList.get(1);
            int book1Id = book1.getIdbook();
            int book2Id = book2.getIdbook();
            Books book1UpdateAantal = booksDAO.updateBooksAantal(book1Id);
            booksListUpdatAantal.add(book1UpdateAantal);
            Books book2UpdateAantal = booksDAO.updateBooksAantal(book2Id);
            booksListUpdatAantal.add(book2UpdateAantal);
        }if(booksList.size() == 3) {
            Books book1 = booksList.get(0);
            Books book2 = booksList.get(1);
            Books book3 = booksList.get(2);
            int book1Id = book1.getIdbook();
            int book2Id = book2.getIdbook();
            int book3Id = book3.getIdbook();
            Books book1UpdateAantal = booksDAO.updateBooksAantal(book1Id);
            booksListUpdatAantal.add(book1UpdateAantal);
            Books book2UpdateAantal = booksDAO.updateBooksAantal(book2Id);
            booksListUpdatAantal.add(book2UpdateAantal);
            Books book3UpdateAantal = booksDAO.updateBooksAantal(book3Id);
            booksListUpdatAantal.add(book3UpdateAantal);
        }

            boeklist = booksListUpdatAantal;
            return this;

    }


    @Override
    public BuilderPurch withReserveringen(Reserveringen reserveringen) {
        reservering = reserveringen;
        return this;
    }

    @Override
    public BuilderPurch withTotalAmount(int totalAmount) {
        totalAmountPurch = totalAmount;
        return this;
    }


    @Override
    public Purchases Build() {

        Purchases purchases = new Purchases();
        purchases.setPurchaseDate(purchaseD);
        purchases.setKlanten(klant);
        purchases.setBooksList(boeklist);
        purchases.setReserveringen(reservering);
        purchases.setTotalPurchaseAmount(totalAmountPurch);


        return purchases;
    }
}
