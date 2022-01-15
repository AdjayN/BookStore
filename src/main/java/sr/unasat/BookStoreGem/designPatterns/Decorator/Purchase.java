package sr.unasat.BookStoreGem.designPatterns.Decorator;


import sr.unasat.BookStoreGem.Entities.Purchases;

public interface Purchase {
    int getCost();
    Purchases getPurchases();
}
