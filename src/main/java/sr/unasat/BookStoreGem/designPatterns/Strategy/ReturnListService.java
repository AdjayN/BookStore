package sr.unasat.BookStoreGem.designPatterns.Strategy;

import sr.unasat.BookStoreGem.Entities.Bestseller;
import sr.unasat.BookStoreGem.Entities.Purchases;
import sr.unasat.BookStoreGem.Entities.Purchases_book;

import java.util.List;

public class ReturnListService {

    private  Strategy strategy;

    public ReturnListService(Strategy strategy) {
        this.strategy = strategy;
    }

    public List<Bestseller> returnListbestseller(List<Purchases_book> purchases_bookList){

        return strategy.BESTSELLERLIST(purchases_bookList);
    }

    public  int returnListSoldReservationBooks(List<Purchases>purchases){

        return strategy.count(purchases);
    }

    public int returnListOmzetPerMaand(List<Purchases>purchases) {

        return strategy.omzetAmountPerMaand(purchases);

    }

}
