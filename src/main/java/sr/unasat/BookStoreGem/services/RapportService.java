package sr.unasat.BookStoreGem.services;

import sr.unasat.BookStoreGem.DAO.PurchaseDAO;
import sr.unasat.BookStoreGem.Entities.Bestseller;
import sr.unasat.BookStoreGem.Entities.Purchases;
import sr.unasat.BookStoreGem.config.JPAConfiguration;
import sr.unasat.BookStoreGem.designPatterns.Strategy.ReturnListBestseller;
import sr.unasat.BookStoreGem.designPatterns.Strategy.ReturnListOmzetPerMaand;
import sr.unasat.BookStoreGem.designPatterns.Strategy.ReturnListService;
import sr.unasat.BookStoreGem.designPatterns.Strategy.ReturnListSoldReservationBooks;

import java.util.List;
import java.util.Scanner;

public class RapportService {
    private Scanner scanner;
    private PurchaseDAO purchaseDAO;

    public RapportService() {
        this.scanner = new Scanner(System.in);
        this.purchaseDAO = new PurchaseDAO(JPAConfiguration.getEntityManager());
    }

    public void rapportMenuService() {
        boolean exit = true;

        while(exit){

            String rapportHeader = "\n-----Purchase----\n";

            System.out.print(rapportHeader + "\nMenu: \n"
                    + 1 + ". Best Seller List.\n"
                    + 2 + ". ReservationBooks sold PerMonth.\n"
                    + 3 + ". Omzet per maand.\n"
                    + 4 + ". Exit\n");

            int selectedOption = scanner.nextInt();

            switch(selectedOption){
                case 1:
                    bestsellerList();

                    break;
                case 2:
                    System.out.println("Enter a  month");
                    int month = scanner.nextInt();
                    soldReservationBooksPerMonth(month);
                    break;
                case 3:
                    omzetPerMaand(1);
                    break;
                case 4:
                    exit = false;
                    break;
                default:
                    System.out.print("\n----Invalid option selected!----\n");
            };

        }
    }
    public List<Bestseller> bestsellerList() {

        ReturnListService returnListService = new ReturnListService(new ReturnListBestseller());
        List<Bestseller> bestSeller = returnListService.returnListbestseller(purchaseDAO.retrievePurchasesIDBook());

        return bestSeller;
    }

    public int soldReservationBooksPerMonth(int month) {

        ReturnListService returnListService = new ReturnListService(new ReturnListSoldReservationBooks());

        switch (month) {
            case 1:
                return returnListService.returnListSoldReservationBooks(purchaseDAO.retrieveJanuaryPurchaseList());
            case 2:
                return returnListService.returnListSoldReservationBooks(purchaseDAO.retrieveFebruaryPurchaseList());
            case 3:
                return returnListService.returnListSoldReservationBooks(purchaseDAO.retrieveMarchPurchaseList());
            case 4:
                return returnListService.returnListSoldReservationBooks(purchaseDAO.retrieveAprilPurchaseList());
            case 5:
                return returnListService.returnListSoldReservationBooks(purchaseDAO.retrieveMayPurchaseList());
            case 6:
                return returnListService.returnListSoldReservationBooks(purchaseDAO.retrieveJunePurchaseList());
            case 7:
                return returnListService.returnListSoldReservationBooks(purchaseDAO.retrieveJulyPurchaseList());
            case 8:
                return returnListService.returnListSoldReservationBooks(purchaseDAO.retrieveAugustPurchaseList());
            case 9:
                return returnListService.returnListSoldReservationBooks(purchaseDAO.retrieveSeptemberPurchaseList());
            case 10:
                return returnListService.returnListSoldReservationBooks(purchaseDAO.retrieveOctoberPurchaseList());
            case 11:
                return returnListService.returnListSoldReservationBooks(purchaseDAO.retrieveNovemberPurchaseList());
            case 12:
                return returnListService.returnListSoldReservationBooks(purchaseDAO.retrieveDecemberPurchaseList());

            default:
                System.out.println("insert the project month");
        }
        return 0;

    }

    public int omzetPerMaand(int month) {

        ReturnListService returnListService = new ReturnListService(new ReturnListOmzetPerMaand());

        switch (month) {
            case 1:
                return returnListService.returnListOmzetPerMaand(purchaseDAO.retrieveJanuaryPurchaseList());
            case 2:
                return returnListService.returnListOmzetPerMaand(purchaseDAO.retrieveFebruaryPurchaseList());
            case 3:
                return returnListService.returnListOmzetPerMaand(purchaseDAO.retrieveMarchPurchaseList());
            case 4:
                return returnListService.returnListOmzetPerMaand(purchaseDAO.retrieveAprilPurchaseList());
            case 5:
                return returnListService.returnListOmzetPerMaand(purchaseDAO.retrieveMayPurchaseList());
            case 6:
                return returnListService.returnListOmzetPerMaand(purchaseDAO.retrieveJunePurchaseList());
            case 7:
                return returnListService.returnListOmzetPerMaand(purchaseDAO.retrieveJulyPurchaseList());
            case 8:
                return returnListService.returnListOmzetPerMaand(purchaseDAO.retrieveAugustPurchaseList());
            case 9:
                return returnListService.returnListOmzetPerMaand(purchaseDAO.retrieveSeptemberPurchaseList());
            case 10:
                return returnListService.returnListOmzetPerMaand(purchaseDAO.retrieveOctoberPurchaseList());
            case 11:
                return returnListService.returnListOmzetPerMaand(purchaseDAO.retrieveNovemberPurchaseList());
            case 12:
                return returnListService.returnListOmzetPerMaand(purchaseDAO.retrieveDecemberPurchaseList());

            default:
                System.out.println("insert the project month");
        }
        return 0;

    }

}

