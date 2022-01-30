package sr.unasat.BookStoreGem.designPatterns.Decorator;


import sr.unasat.BookStoreGem.Entities.Books;
import sr.unasat.BookStoreGem.Entities.Purchases;

import java.util.List;

public class BasicPurchase implements Purchase {

    private Purchases purch;

    public BasicPurchase(Purchases purch) {
        this.purch = purch;
    }

    @Override
    public int getCost() {
        List<Books> booksList = purch.getBooksList();
        if (booksList.size()==1){

            Books book1 = booksList.get(0);
            int bookPrijs = book1.getPrijs();
            return bookPrijs;
        }
        return 0;

    }

    @Override
    public Purchases getPurchases() {
        return purch;
    }
}
