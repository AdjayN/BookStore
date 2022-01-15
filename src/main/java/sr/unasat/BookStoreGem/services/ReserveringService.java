package sr.unasat.BookStoreGem.services;

import sr.unasat.BookStoreGem.DAO.BooksDAO;
import sr.unasat.BookStoreGem.DAO.KlantenDAO;
import sr.unasat.BookStoreGem.DAO.ReserveringenDAO;
import sr.unasat.BookStoreGem.Entities.Books;
import sr.unasat.BookStoreGem.Entities.Klanten;
import sr.unasat.BookStoreGem.Entities.Reserveringen;
import sr.unasat.BookStoreGem.config.JPAConfiguration;
import sr.unasat.BookStoreGem.designPatterns.Builder.ConcreteBuilders.ConcreteBuilderReser;
import sr.unasat.BookStoreGem.designPatterns.Builder.Director;
import sr.unasat.BookStoreGem.designPatterns.Builder.Interfaces.BuilderReser;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReserveringService {

    private Scanner scanner;
    private ReserveringenDAO reserveringenDAO;
    private KlantenDAO klantenDAO;
    private BooksDAO booksDAO;
    private Director director;

    public ReserveringService() {
        this.scanner = new Scanner(System.in);
        this.reserveringenDAO = new ReserveringenDAO(JPAConfiguration.getEntityManager());
        this.klantenDAO = new KlantenDAO(JPAConfiguration.getEntityManager());
        this.booksDAO  = new BooksDAO(JPAConfiguration.getEntityManager());
        this.director  = new Director();
    }

    public void reserveringMenuService() {

        boolean exit = true;

        while(exit){

            String reservingHeader = "\n-----Reserveringen----\n";

            System.out.print(reservingHeader + "\nMenu: \n"
                    + 1 + ". Select a Reservation.\n"
                    + 2 + ". Add Reservation.\n"
                    + 3 + ". Update Reservation.\n"
                    + 4 + ". Delete Reservation.\n"
                    + 5 + ". Select all Reservations.\n"
                    + 6 + ". Exit\n");

            int selectedOption = scanner.nextInt();

            switch(selectedOption){
                case 1:
                    System.out.println("Select on");
                    break;
                case 2:
                    Reserveringen reservering = createReservering();
                    addReservering(reservering);
                    break;
                case 3:
                    System.out.println("Update");
                    break;
                case 4:
                    System.out.println("Delete");
                    break;
                case 5:
                    System.out.println("Select all");
                    break;
                case 6:
                    exit = false;
                    break;
                default:
                    System.out.print("\n----Invalid option selected!----\n");
            };

        }
    }

    private Reserveringen createReservering() {

        Reserveringen reserveringen = new Reserveringen();

        System.out.println("\n Add a reservation:\n");

        System.out.println("Enter a reservation date");
        scanner.nextLine();
        String reservationDate = scanner.nextLine();
        reserveringen.setReservatieDatum(reservationDate);

        System.out.println("Enter a klant id");
        int klantId = scanner.nextInt();
        Klanten klant = new Klanten();
        klant.setIdKlanten(klantId);
        reserveringen.setKlanten(klant);

        boolean bookSelected = false;

        List<Books> booksList = new ArrayList<>();

        while (!bookSelected) {

            System.out.print(
                    + 1 + ". Add a book.\n"
                    + 2 + ". Finish adding books\n");

            int selectedOption = scanner.nextInt();

            switch(selectedOption){
                case 1:
                    if (booksList.size() < 4) {
                        System.out.println("Enter a book id");
                        Books book = new Books();
                        int bookId = scanner.nextInt();
                        book.setIdbook(bookId);
                        booksList.add(book);
                    } else {
                        System.out.println("Max amount of books added");
                    }
                    break;
                case 2:
                    bookSelected = true;
                    break;
                default:
                    System.out.print("\n----Invalid option selected!----\n");
            };

            reserveringen.setBooksList(booksList);

        }

        return reserveringen;
    }

    // add new reservering record to database
    public Reserveringen addReservering(Reserveringen reserveringen){

        int klantId = reserveringen.getKlanten().getIdKlanten();
        Klanten klant = klantenDAO.selectKlantenById(klantId);

        List<Books> bookListWithId = reserveringen.getBooksList();
        List<Books> booksList = new ArrayList<>();

        int totalAmount = 0;

        if(bookListWithId.size() == 1){
            Books book1WithId = bookListWithId.get(0);
            int book1Id = book1WithId.getIdbook();
            Books book1 = booksDAO.selectBooksById(book1Id);
            booksList.add(book1);
            totalAmount = book1.getPrijs();
        }if(bookListWithId.size() == 2){
            Books book1WithId = bookListWithId.get(0);
            Books book2WithId = bookListWithId.get(1);
            int book1Id = book1WithId.getIdbook();
            Books book1 = booksDAO.selectBooksById(book1Id);
            int book2Id = book2WithId.getIdbook();
            Books book2 = booksDAO.selectBooksById(book2Id);
            booksList.add(book1);
            booksList.add(book2);
            totalAmount = book1.getPrijs() + book2.getPrijs();
        }if(bookListWithId.size() == 3){
            Books book1WithId = bookListWithId.get(0);
            Books book2WithId = bookListWithId.get(1);
            Books book3WithId = bookListWithId.get(2);
            int book1Id = book1WithId.getIdbook();
            Books book1 = booksDAO.selectBooksById(book1Id);
            int book2Id = book2WithId.getIdbook();
            Books book2 = booksDAO.selectBooksById(book2Id);
            int book3Id = book3WithId.getIdbook();
            Books book3 = booksDAO.selectBooksById(book3Id);
            booksList.add(book1);
            booksList.add(book2);
            booksList.add(book3);
            totalAmount = book1.getPrijs() + book2.getPrijs()+ book3.getPrijs();
        }

        reserveringen.setTotal_amount(totalAmount);
        reserveringen.setKlanten(klant);
        reserveringen.setBooksList(booksList);

        //Builder
        BuilderReser builderReser = new ConcreteBuilderReser();
        Reserveringen reserveringNew = director.DirectBuildReser(builderReser,reserveringen);

        return reserveringenDAO.create(reserveringNew);
    }
}
