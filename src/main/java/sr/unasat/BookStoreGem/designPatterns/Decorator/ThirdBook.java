package sr.unasat.BookStoreGem.designPatterns.Decorator;


import sr.unasat.BookStoreGem.Entities.Books;
import sr.unasat.BookStoreGem.Entities.Purchases;

import java.util.List;

public class ThirdBook implements Purchase {

    private Purchase tempPurch;
    private Purchases Purch;

    public ThirdBook(Purchase tempPurch) {
        this.tempPurch = tempPurch;
    }

    @Override
    public int getCost() {
        List<Books> booksList = tempPurch.getPurchases().getBooksList();

        if (booksList.size()<4){

            Books book3 = booksList.get(2);
            int bookPrijs3 = book3.getPrijs();
            return tempPurch.getCost() + bookPrijs3;
        }
        return 0;

    }

    @Override
    public Purchases getPurchases() {
        Purch = tempPurch.getPurchases();
        return Purch;
    }
}
