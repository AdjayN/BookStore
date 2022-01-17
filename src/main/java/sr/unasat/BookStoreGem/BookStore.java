package sr.unasat.BookStoreGem;

import sr.unasat.BookStoreGem.services.*;

import java.util.Scanner;

public class BookStore {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        boolean endProgram = true;

        ReserveringService reserveringService = new ReserveringService();
        PurchasesService purchasesService = new PurchasesService();
        RapportService rapportService = new RapportService();
        KlantenService klantenService = new KlantenService();
        BookService bookService = new BookService();

        while (endProgram) {

            String greetingHeader = "\n-----BookStore Adjay----\n";

            System.out.print(greetingHeader + "\nMenu: \n"
                    + 1 + ". Reservation.\n"
                    + 2 + ". Purchases.\n"
                    + 3 + ". Klanten database.\n"
                    + 4 + ". Book database.\n"
                    + 5 + ". Rapports.\n"
                    + 6 + ". Exit\n");

            int selectedOption = scanner.nextInt();

            switch (selectedOption) {
                case 1:
                    reserveringService.reserveringMenuService();
                    break;
                case 2:
                    System.out.println("Go to Purchases");
                    purchasesService.purchaseMenuService();
                    break;
                case 3:
                    System.out.println("Go to Guest database");
                    scanner.nextLine();
                    klantenService.klantenMenuService();
                    break;
                case 4:
                    System.out.println("Go to Book database");
                    scanner.nextLine();
                    bookService.booksMenuService();
                    break;
                case 5:
                    System.out.println("Go to Rapport");
                    rapportService.rapportMenuService();
                    break;
                case 6:
                    endProgram = false;
                    break;
                default:
                    System.out.print("\n----Invalid option selected!----\n");
            }
            ;

        }
    }
}


