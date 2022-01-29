package sr.unasat.BookStoreGem.designPatterns.Strategy;

import sr.unasat.BookStoreGem.Entities.Bestseller;
import sr.unasat.BookStoreGem.Entities.Purchases;
import sr.unasat.BookStoreGem.Entities.Purchases_book;

import java.util.Arrays;
import java.util.List;

public class ReturnListOmzetPerMaand implements Strategy {
    @Override
    public List<Bestseller> BESTSELLERLIST(List<Purchases_book> purchases_booksList) {
        return null;
    }

    @Override
    public int count(List<Purchases> purchasesList) {
        return 0;
    }

    @Override
    public int omzetAmountPerMaand(List<Purchases> purchases) {

        System.out.println("---------------Totaal Omzet per maand-----------");

        Purchases[] purchasesArr = purchases.toArray(new Purchases[0]);

        int size = purchasesArr.length;
        boolean visited[] = new boolean[size];
        Arrays.fill(visited,false);
        int total = 0; //check

        for(int i = 0; i<size ; i++){

            if (purchasesArr[i].getReserveringen() == null) {

                System.out.println("Purchase ID: " + purchasesArr[i].getIdPurchase() + " Amount of books: " +
                        purchasesArr[i].getBooksList().size() + " Subtotal: SRD" +
                        purchasesArr[i].getTotalPurchaseAmount());

            } else {

                System.out.println("Purchase ID: " + purchasesArr[i].getIdPurchase() + " Amount of books: " +
                        purchasesArr[i].getReserveringen().getBooksList().size() + " Subtotal: SRD" +
                        purchasesArr[i].getTotalPurchaseAmount());
            }


            total+=purchasesArr[i].getTotalPurchaseAmount();

        }

        System.out.println("------------------------------------------------");
        System.out.println("Total amount: SRD\t" + total);


        return total;
    }
}
