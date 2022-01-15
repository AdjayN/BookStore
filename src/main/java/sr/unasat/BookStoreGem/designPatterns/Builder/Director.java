package sr.unasat.BookStoreGem.designPatterns.Builder;

import sr.unasat.BookStoreGem.Entities.Purchases;
import sr.unasat.BookStoreGem.Entities.Reserveringen;
import sr.unasat.BookStoreGem.designPatterns.Builder.Interfaces.BuilderPurch;
import sr.unasat.BookStoreGem.designPatterns.Builder.Interfaces.BuilderReser;

public class Director {


    public Director() {
    }

    public Reserveringen DirectBuildReser(BuilderReser builderReser, Reserveringen reserveringen){

        //de builder withReservatieDatum build de part voor reservatieDatm(String)
        return builderReser.withReservatieDatum(reserveringen.getReservatieDatum())//Returned wat de interface build
                .withKlanten(reserveringen.getKlanten())
                .withBooks(reserveringen.getBooksList())
                .withTotalAmount(reserveringen.getTotal_amount())
                .Build();

    }

    public Purchases DirectBuildPurchase(BuilderPurch builderPurch, Purchases purchases){

        return builderPurch.withPurchaseDatum(purchases.getPurchaseDate())
                .withKlanten(purchases.getKlanten())
                .withBooks(purchases.getBooksList())
                .withTotalAmount(purchases.getTotalPurchaseAmount())
                .Build();

    }


    public Purchases DirectBuildPurchWithReservation(BuilderPurch builderPurch, Purchases purchases){

        return builderPurch.withPurchaseDatum(purchases.getPurchaseDate())
                .withKlanten(purchases.getReserveringen().getKlanten())
                .withBooks(purchases.getReserveringen().getBooksList())
                .withReserveringen(purchases.getReserveringen())
                .withTotalAmount(purchases.getReserveringen().getTotal_amount())
                .Build();

    }




}
