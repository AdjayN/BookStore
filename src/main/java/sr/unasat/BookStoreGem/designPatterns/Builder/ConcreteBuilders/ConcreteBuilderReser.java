package sr.unasat.BookStoreGem.designPatterns.Builder.ConcreteBuilders;


import sr.unasat.BookStoreGem.DAO.BooksDAO;
import sr.unasat.BookStoreGem.Entities.Books;
import sr.unasat.BookStoreGem.Entities.Klanten;
import sr.unasat.BookStoreGem.Entities.Reserveringen;
import sr.unasat.BookStoreGem.config.JPAConfiguration;
import sr.unasat.BookStoreGem.designPatterns.Builder.Interfaces.BuilderReser;

import java.util.ArrayList;
import java.util.List;

//concrete builders. de implementatons of the methods to build the different parts
public class ConcreteBuilderReser implements BuilderReser {

    //(uitleg van withReservatieDatum method)de reservatieDatum(String) value is de value dat naar binnen komt als de
    //withReservatiedatum method word gebruikt.Deze reservatieDatum(String)
    //wordt assigned naar de class String variable(reservatieD).

    //als deze method wordt gebruikt returned het de class String value(reserveringD)

    BooksDAO booksDAO = new BooksDAO(JPAConfiguration.getEntityManager());

    private String reservatieD; //deze variable behoor tot de class
    private Klanten klant;
    private List<Books> boeklist;
    private int totalAmountRes;



    @Override
    public BuilderReser withReservatieDatum(String reservatieDatum) {
        reservatieD = reservatieDatum;
        return this;
    }

    @Override
    public BuilderReser withKlanten(Klanten klanten) {
        klant = klanten;
        return this; // this = klant
    }

    @Override
    public BuilderReser withBooks(List<Books> booksList) {

        List<Books> booksListUpdateGereserveerdAantal = new ArrayList<>();

        if(booksList.size() == 1){
            Books book1 = booksList.get(0);
            int book1Id = book1.getIdbook();
            Books book1UpdateBooksReserveringAantal = booksDAO.updateBooksReserveringAantal(book1Id,+1);
            booksListUpdateGereserveerdAantal.add(book1UpdateBooksReserveringAantal);
        }if(booksList.size() == 2){
            Books book1 = booksList.get(0);
            Books book2 = booksList.get(1);
            int book1Id = book1.getIdbook();
            int book2Id = book2.getIdbook();
            Books book1UpdateBooksReserveringAantal = booksDAO.updateBooksReserveringAantal(book1Id,+1);
            Books book2UpdateBooksReserveringAantal = booksDAO.updateBooksReserveringAantal(book2Id,+1);
            booksListUpdateGereserveerdAantal.add(book1UpdateBooksReserveringAantal);
            booksListUpdateGereserveerdAantal.add(book2UpdateBooksReserveringAantal);
        }if(booksList.size() == 3){
            Books book1 = booksList.get(0);
            Books book2 = booksList.get(1);
            Books book3 = booksList.get(2);
            int book1Id = book1.getIdbook();
            int book2Id = book2.getIdbook();
            int book3Id = book3.getIdbook();
            Books book1UpdateBooksReserveringAantal = booksDAO.updateBooksReserveringAantal(book1Id,+1);
            Books book2UpdateBooksReserveringAantal = booksDAO.updateBooksReserveringAantal(book2Id,+1);
            Books book3UpdateBooksReserveringAantal = booksDAO.updateBooksReserveringAantal(book3Id,+1);
            booksListUpdateGereserveerdAantal.add(book1UpdateBooksReserveringAantal);
            booksListUpdateGereserveerdAantal.add(book2UpdateBooksReserveringAantal);
            booksListUpdateGereserveerdAantal.add(book3UpdateBooksReserveringAantal);
        }

        boeklist = booksListUpdateGereserveerdAantal;
        return this;
    }


    @Override
    public BuilderReser withTotalAmount(int totalAmount) {
        totalAmountRes = totalAmount;
        return this;
    }

    //build method bouwt de reserveringen objecten, build methods assigned the class field
    //variable aan een reserverig object en returned een complete reservatie object(product)
    @Override
    public Reserveringen Build() {

        //we maken een new reserveringObject. en gebruiken de setter method om de class field values
        //toe te voege aan het new reservatie object en returned een complete reservatieObject(product)
        Reserveringen reserveringen = new Reserveringen();
        reserveringen.setReservatieDatum(reservatieD);
        reserveringen.setKlanten(klant);
        reserveringen.setBooksList(boeklist);
        reserveringen.setTotal_amount(totalAmountRes);

        return reserveringen;
    }
}
