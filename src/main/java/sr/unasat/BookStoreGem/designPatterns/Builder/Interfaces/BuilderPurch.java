package sr.unasat.BookStoreGem.designPatterns.Builder.Interfaces;


import sr.unasat.BookStoreGem.Entities.Books;
import sr.unasat.BookStoreGem.Entities.Klanten;
import sr.unasat.BookStoreGem.Entities.Purchases;
import sr.unasat.BookStoreGem.Entities.Reserveringen;

import java.util.List;

public interface BuilderPurch {

    BuilderPurch withPurchaseDatum (String purchaseDatum);
    BuilderPurch withKlanten(Klanten klanten);
    BuilderPurch withBooks (List<Books> booksList);
    BuilderPurch withReserveringen(Reserveringen reserveringen);
    BuilderPurch withTotalAmount(int totalAmount);

    Purchases Build();
}
