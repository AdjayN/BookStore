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

public class ConcreteBuilderPurchWithReservationId implements BuilderPurch {

    BooksDAO booksDAO = new BooksDAO(JPAConfiguration.getEntityManager());
//TODO remove with ID
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
    public BuilderPurch withBooks(List<Books> reservationBooksList) {

        List<Books> purchaseBooksList = new ArrayList<>();

        if(reservationBooksList.size() == 1){
            Books book1 = reservationBooksList.get(0);

            int book1Id = book1.getIdbook();

            purchaseBooksList.add(book1);


        }if(reservationBooksList.size() == 2){
            Books book1 = reservationBooksList.get(0);
            Books book2 = reservationBooksList.get(1);

            int book1Id = book1.getIdbook();
            int book2Id = book2.getIdbook();


            purchaseBooksList.add(book1);
            purchaseBooksList.add(book2);

        }if(reservationBooksList.size() == 3){
            Books book1 = reservationBooksList.get(0);
            Books book2 = reservationBooksList.get(1);
            Books book3 = reservationBooksList.get(2);

            int book1Id = book1.getIdbook();
            int book2Id = book2.getIdbook();
            int book3Id = book3.getIdbook();

            purchaseBooksList.add(book1);
            purchaseBooksList.add(book2);
            purchaseBooksList.add(book3);

        }

        boeklist = purchaseBooksList;
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
