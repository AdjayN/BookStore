package sr.unasat.BookStoreGem.services;

import sr.unasat.BookStoreGem.DAO.BooksDAO;
import sr.unasat.BookStoreGem.DAO.KlantenDAO;
import sr.unasat.BookStoreGem.DAO.PurchaseDAO;
import sr.unasat.BookStoreGem.Entities.Books;
import sr.unasat.BookStoreGem.Entities.Klanten;
import sr.unasat.BookStoreGem.Entities.Purchases;
import sr.unasat.BookStoreGem.config.JPAConfiguration;
import sr.unasat.BookStoreGem.designPatterns.Builder.ConcreteBuilders.ConcreteBuilderPurch;
import sr.unasat.BookStoreGem.designPatterns.Builder.Director;
import sr.unasat.BookStoreGem.designPatterns.Builder.Interfaces.BuilderPurch;
import sr.unasat.BookStoreGem.designPatterns.Decorator.BasicPurchase;
import sr.unasat.BookStoreGem.designPatterns.Decorator.Purchase;
import sr.unasat.BookStoreGem.designPatterns.Decorator.SecondBook;
import sr.unasat.BookStoreGem.designPatterns.Decorator.ThirdBook;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class KlantenService {

    private Scanner scanner;
    private KlantenDAO klantenDAO;

    public KlantenService() {
        this.scanner = new Scanner(System.in);
        this.klantenDAO = new KlantenDAO(JPAConfiguration.getEntityManager());
    }

    public void klantenMenuService() {
        boolean exit = true;

        while(exit){

            String klantenHeader = "\n-----Klanten----\n";

            System.out.print(klantenHeader + "\nMenu: \n"
                    + 1 + ". Select a Klanten.\n"
                    + 2 + ". Add Klanten.\n"
                    + 3 + ". Update Klanten.\n"
                    + 4 + ". Delete Klanten.\n"
                    + 5 + ". Select all Klanten.\n"
                    + 6 + ". Exit\n");

            int selectedOption = scanner.nextInt();

            switch(selectedOption){
                case 1:
                    System.out.println("Select on");
                    break;
                case 2:
                    Klanten klant = createKlanten();
                    addKlant(klant);
                    break;
                case 3:
                    System.out.println("Update klant");
                    System.out.println("Enter old klant id");
                    int oldKlantId = scanner.nextInt();
                    Klanten klantUpdated = getUpdateKlantInfo();
                    updateKlant(oldKlantId,klantUpdated);
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

    private Klanten getUpdateKlantInfo() {

        Klanten klant = new Klanten();

        scanner.nextLine();
        System.out.println("Enter new customer name");
        String newCustomerName = scanner.nextLine();

        klant.setNaam(newCustomerName);

        System.out.println("Enter new customer email");
        String newCustomerEmail = scanner.nextLine();

        klant.setEmailaddres(newCustomerEmail);

        return klant;
    }

    private Klanten createKlanten() {

        Klanten klant = new Klanten();

        System.out.println("\n Add a customer:\n");

        scanner.nextLine();
        System.out.println("Enter a customer name");
        String customerName = scanner.nextLine();

        klant.setNaam(customerName);

        System.out.println("Enter a customer email");
        String customerEmail = scanner.nextLine();

        klant.setEmailaddres(customerEmail);

        return klant;
    }

    public List<Klanten> getKlantenList(){

        List<Klanten> klantenList = klantenDAO.retrievenKlantenlist();
        return klantenList;

    }

    public Klanten getKlant(int id){

        Klanten klant = klantenDAO.selectKlantenById(id);
        return klant;
    }

    // add new klant record to database
    public Klanten addKlant(Klanten klanten){
        return klantenDAO.create(klanten);
    }

    // Update klant record from database
    public Klanten updateKlant(int id, Klanten klanten){
        return klantenDAO.updateklanten(id,klanten);
    }

    // Delete klant record from databse
    public void deleteKlant(int id){
        klantenDAO.delete(id);
    }

}

