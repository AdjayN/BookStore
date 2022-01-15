package sr.unasat.BookStoreGem.designPatterns.Strategy;

import sr.unasat.BookStoreGem.Entities.Bestseller;
import sr.unasat.BookStoreGem.Entities.Purchases;
import sr.unasat.BookStoreGem.Entities.Purchases_book;

import java.util.List;

public interface Strategy {

    List<Bestseller> BESTSELLERLIST(List<Purchases_book> purchases_booksList);

    int count (List<Purchases>purchasesList);


    int omzetAmountPerMaand (List<Purchases>purchases);



}
