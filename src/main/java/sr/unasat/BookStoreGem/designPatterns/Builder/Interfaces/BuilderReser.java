package sr.unasat.BookStoreGem.designPatterns.Builder.Interfaces;


import sr.unasat.BookStoreGem.Entities.Books;
import sr.unasat.BookStoreGem.Entities.Klanten;
import sr.unasat.BookStoreGem.Entities.Reserveringen;

import java.util.List;

//dit is een abstrac builder(van hieruit roep je methods op om een object te builden)
//Methods to build parts of the product

public interface BuilderReser {

    //Elk builder maakt zijn eigen part, het accepteerd in zijn parameters
    //de waarde dat nodig is omm de part te builden

    BuilderReser withReservatieDatum (String reservatieDatum);
    BuilderReser withKlanten(Klanten klanten);
    BuilderReser withBooks (List<Books> booksList);
    BuilderReser withTotalAmount(int totalAmount);

    //method to "assemble" the final product
    Reserveringen Build();

}
