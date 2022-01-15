package sr.unasat.BookStoreGem.services;

import sr.unasat.BookStoreGem.DAO.BooksDAO;
import sr.unasat.BookStoreGem.DAO.KlantenDAO;
import sr.unasat.BookStoreGem.DAO.PurchaseDAO;
import sr.unasat.BookStoreGem.Entities.Books;
import sr.unasat.BookStoreGem.Entities.Klanten;
import sr.unasat.BookStoreGem.Entities.Purchases;
import sr.unasat.BookStoreGem.Entities.Reserveringen;
import sr.unasat.BookStoreGem.config.JPAConfiguration;
import sr.unasat.BookStoreGem.designPatterns.Builder.ConcreteBuilders.ConcreteBuilderPurch;
import sr.unasat.BookStoreGem.designPatterns.Builder.ConcreteBuilders.ConcreteBuilderReser;
import sr.unasat.BookStoreGem.designPatterns.Builder.Director;
import sr.unasat.BookStoreGem.designPatterns.Builder.Interfaces.BuilderPurch;
import sr.unasat.BookStoreGem.designPatterns.Builder.Interfaces.BuilderReser;
import sr.unasat.BookStoreGem.designPatterns.Decorator.BasicPurchase;
import sr.unasat.BookStoreGem.designPatterns.Decorator.Purchase;
import sr.unasat.BookStoreGem.designPatterns.Decorator.SecondBook;
import sr.unasat.BookStoreGem.designPatterns.Decorator.ThirdBook;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PurchasesService {
    private Scanner scanner;
    private KlantenDAO klantenDAO;
    private BooksDAO booksDAO;
    private Director director;
    private PurchaseDAO purchaseDAO;

    public PurchasesService() {
        this.scanner = new Scanner(System.in);
        this.purchaseDAO = new PurchaseDAO(JPAConfiguration.getEntityManager());
        this.klantenDAO = new KlantenDAO(JPAConfiguration.getEntityManager());
        this.booksDAO  = new BooksDAO(JPAConfiguration.getEntityManager());
        this.director  = new Director();


        }
    public void purchaseMenuService() {
        boolean exit = true;

        while(exit){

            String purchaseHeader = "\n-----Purchase----\n";

            System.out.print(purchaseHeader + "\nMenu: \n"
                    + 1 + ". Select a Purchase.\n"
                    + 2 + ". Add Purchase.\n"
                    + 3 + ". Update Purchase.\n"
                    + 4 + ". Delete Purchase.\n"
                    + 5 + ". Select all Purchase.\n"
                    + 6 + ". Exit\n");

            int selectedOption = scanner.nextInt();

            switch(selectedOption){
                case 1:
                    System.out.println("Select on");
                    break;
                case 2:
                    Purchases purchase = createPurchase();
                    addPurchase(purchase);
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


    private Purchases createPurchase() {

        Purchases purchases = new Purchases();


        System.out.println("\n Add a purchase:\n");

        System.out.println("Enter a klant id");
        int klantId = scanner.nextInt();
        Klanten klant = new Klanten();
        klant.setIdKlanten(klantId);
        purchases.setKlanten(klant);


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

            purchases.setBooksList(booksList);

        }

        return purchases;
    }

    // add new purchase record to database
    public Purchases addPurchase(Purchases purchases){


        int klantId = purchases.getKlanten().getIdKlanten();
        Klanten klant = klantenDAO.selectKlantenById(klantId);

        List<Books> bookListWithId = purchases.getBooksList();
        List<Books> booksList = new ArrayList<>();

        if(bookListWithId.size() == 1){
            Books book1WithId = bookListWithId.get(0);
            int book1Id = book1WithId.getIdbook();
            Books book1 = booksDAO.selectBooksById(book1Id);
            booksList.add(book1);

        }if(bookListWithId.size() == 2){
            Books book1WithId = bookListWithId.get(0);
            Books book2WithId = bookListWithId.get(1);
            int book1Id = book1WithId.getIdbook();
            Books book1 = booksDAO.selectBooksById(book1Id);
            int book2Id = book2WithId.getIdbook();
            Books book2 = booksDAO.selectBooksById(book2Id);
            booksList.add(book1);
            booksList.add(book2);

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

        }

        purchases.setKlanten(klant);
        purchases.setBooksList(booksList);

        //decorator
        Purchase purchase = new ThirdBook(new SecondBook(new BasicPurchase(purchases)));
        //Purchase purchase = new  BasicPurchase(purchases);
        int totalAmountAll = purchase.getCost();
        purchases.setTotalPurchaseAmount(totalAmountAll);

        //builder
        BuilderPurch builderPurch = new ConcreteBuilderPurch();
        Purchases purchaseNew = director.DirectBuildPurchase(builderPurch,purchases);

        return purchaseDAO.create(purchaseNew);
    }





}



