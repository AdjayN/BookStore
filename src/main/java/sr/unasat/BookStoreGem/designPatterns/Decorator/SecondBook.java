package sr.unasat.BookStoreGem.designPatterns.Decorator;


import sr.unasat.BookStoreGem.Entities.Books;
import sr.unasat.BookStoreGem.Entities.Purchases;

import java.util.List;

public class SecondBook implements Purchase {

    private Purchase tempPurch;
    private Purchases Purch;

    public SecondBook(Purchase tempPurch) {
        this.tempPurch = tempPurch;
    }

    @Override
    public int getCost() {
        List<Books> booksList = tempPurch.getPurchases().getBooksList();

        if (booksList.size()<4){

            Books book2 = booksList.get(1);
            int bookPrijs2 = book2.getPrijs();
            return tempPurch.getCost() + bookPrijs2;
        }

       return 0;

    }

    @Override
    public Purchases getPurchases() {
       Purch = tempPurch.getPurchases();
       return Purch;
    }
}
