package sr.unasat.BookStoreGem.designPatterns.Strategy;

import sr.unasat.BookStoreGem.Entities.Bestseller;
import sr.unasat.BookStoreGem.Entities.Purchases;
import sr.unasat.BookStoreGem.Entities.Purchases_book;

import java.util.Arrays;
import java.util.List;

public class ReturnListSoldReservationBooks implements Strategy {


    @Override
    public List<Bestseller> BESTSELLERLIST(List<Purchases_book> purchases_booksList) {
        return null;
    }

    @Override
    public int count(List<Purchases> purchasesList) {

        System.out.println("---------------Sold reservations-----------");

        Purchases[] purchasesArr = purchasesList.toArray(new Purchases[0]);

        int size = purchasesArr.length;
        boolean visited[] = new boolean[size];
        Arrays.fill(visited,false);
        int count = 1; //check
        for( int i = 0; i < size ; i++){

            if(purchasesArr[i].getReserveringen() != null && purchasesArr[i].getReserveringen().getIdReservatie() > 1){
                count++;
            }

        }
        System.out.println("Amount reservation books sold this month:" + count);


        System.out.println("----------------------------------");

        return count;
    }

    @Override
    public int omzetAmountPerMaand(List<Purchases> purchases) {
        return 0;
    }
}
