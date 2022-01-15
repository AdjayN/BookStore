package sr.unasat.BookStoreGem.DAO;

import sr.unasat.BookStoreGem.Entities.*;
import sr.unasat.BookStoreGem.config.JPAConfiguration;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class PurchaseDAO {

    private EntityManager entityManager;
    EntityTransaction transaction = null;

    public PurchaseDAO(EntityManager entityManager) {this.entityManager = entityManager;}

    BooksDAO booksDAO = new BooksDAO(JPAConfiguration.getEntityManager());

    public Purchases create (Purchases purchases){

        try {
            //get a transaction
            transaction = entityManager.getTransaction();
            //begin transaction
            transaction.begin();
            //save the purchaseobject
            entityManager.persist(purchases);

            //commit the transaction
            transaction.commit();
            String naam = purchases.getKlanten().getNaam();
            int totalAmount = purchases.getTotalPurchaseAmount();
            int purchaseId = purchases.getIdPurchase();
            List<Books> booksList = purchases.getBooksList();

            if(booksList.size() == 1){
                Books book1 = booksList.get(0);
                int prijsBook1 = book1.getPrijs();
                String titelBook1 = book1.getTitel();

                System.out.println("Purchase ID: " + purchaseId + " Klant :"+naam +
                        "\n"+ "-----------------Purchase-------------" +
                        "\n" + "Book Title " + titelBook1 + " \tPrijs: " + prijsBook1 +
                        "\n" + "-------------------------------------" +
                        "\n" + "\t\t\t    Total " + totalAmount);

            }if(booksList.size() == 2){
                Books book1 = booksList.get(0);
                int prijsBook1 = book1.getPrijs();
                String titelBook1 = book1.getTitel();
                Books book2 = booksList.get(1);
                int prijsBook2 = book2.getPrijs();
                String titelBook2 = book2.getTitel();

                System.out.println("Purchase ID: " + purchaseId + " Klant :"+naam +
                        "\n"+ "-----------------Purchase-------------" +
                        "\n" + "Book Title " + titelBook1 + " \tPrijs: " + prijsBook1 +
                        "\n" + "Book Title " + titelBook2 + " \tPrijs: " + prijsBook2 +
                        "\n" + "-------------------------------------" +
                        "\n" + "\t\t\t    Total " + totalAmount);

            }if(booksList.size() == 3){
                Books book1 = booksList.get(0);
                int prijsBook1 = book1.getPrijs();
                String titelBook1 = book1.getTitel();
                Books book2 = booksList.get(1);
                int prijsBook2 = book2.getPrijs();
                String titelBook2 = book2.getTitel();
                Books book3 = booksList.get(2);
                int prijsBook3 = book3.getPrijs();
                String titelBook3 = book3.getTitel();

                System.out.println("Purchase ID: " + purchaseId + " Klant :"+naam +
                        "\n"+ "-----------------Purchase-------------" +
                        "\n" + "Book Title " + titelBook1 + " \tPrijs: " + prijsBook1 +
                        "\n" + "Book Title " + titelBook2 + " \tPrijs: " + prijsBook2 +
                        "\n" + "Book Title " + titelBook3 + " \tPrijs: " + prijsBook3 +
                        "\n" + "-------------------------------------" +
                        "\n" + "\t\t\t    Total " + totalAmount);

            }

        }catch (Exception e){
            if(transaction!=null){
                transaction.rollback();
            }
            System.out.println("rollback transaction");
            e.printStackTrace();
        }
        return purchases;
    }
    public Purchases updatePurchases(int id, Purchases purchases){
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            Purchases purchase = entityManager.find(Purchases.class,id);

            // aantallen van de boeken die al eerder geboekt was wordt +1 opgeteld
            List<Books> bookList = purchase.getBooksList();

            if(bookList.size() == 1){
                Books book1 = bookList.get(0);
                book1.setAantal(book1.getAantal() + 1);
            }
            if(bookList.size() == 2){
                Books book1 = bookList.get(0);
                Books book2 = bookList.get(1);
                book1.setAantal(book1.getAantal() + 1);
                book2.setAantal(book2.getAantal() + 1);
            }if(bookList.size() == 3){
                Books book1 = bookList.get(0);
                Books book2 = bookList.get(1);
                Books book3 = bookList.get(2);
                book1.setAantal(book1.getAantal() + 1);
                book2.setAantal(book2.getAantal() + 1);
                book3.setAantal(book3.getAantal() + 1);
            }

            purchase.setPurchaseDate(purchases.getPurchaseDate());
            purchase.setKlanten(purchases.getKlanten());
            purchase.setBooksList(purchases.getBooksList());
            purchase.setReserveringen(purchases.getReserveringen());
            purchase.setTotalPurchaseAmount(purchases.getTotalPurchaseAmount());

            entityManager.merge(purchase);

            transaction.commit();
            System.out.println("purchase record : " +id + " has been updated");

            return purchase;

        }catch ( Exception e) {
            // if there are any exceptions, roll back the changes
            if(transaction != null){
                transaction.rollback();
            }
            //pritn the exception
            e.printStackTrace();
        }
        return  null;
    }

    public void delete(int id){
        try{
            transaction = entityManager.getTransaction();
            transaction.begin();

            // get Purchase id
           Purchases purchases = entityManager.find(Purchases.class,id);

            entityManager.remove(purchases);

            //commit transaction
            transaction.commit();
            System.out.println("record: " +id + " has been deleted");

        }catch (Exception e) {
            // if there are any exceptions, roll back the changes
            if (transaction != null){
                transaction.rollback();
                e.printStackTrace();
                System.out.println("could not delete record");
            }
        }

    }

    public List<Purchases> retrievePurchaseList(){

        entityManager.getTransaction().begin();
        String jpql = "select p from Purchases p";
        TypedQuery<Purchases> query = entityManager.createQuery(jpql,Purchases.class);
        List<Purchases> PurchaseList = query.getResultList();
        entityManager.getTransaction().commit();
        return PurchaseList;
    }

    public List<Purchases_book> retrievePurchasesIDBook(){

        entityManager.getTransaction().begin();
        String jpql = "select p from purchases_books p";
        TypedQuery<Purchases_book> query = entityManager.createQuery(jpql,Purchases_book.class);
        List<Purchases_book> purchases_bookList = query.getResultList();
        entityManager.getTransaction().commit();
        return purchases_bookList;
    }

    public Purchases selectPurchaseById(int id){

        Purchases purchases = entityManager.find(Purchases.class,id);
        return  purchases;
    }

    public List<Purchases> retrieveJanuaryPurchaseList(){

        entityManager.getTransaction().begin();
        String jpql = "select p from Purchases p where p.purchaseDate between '2020-01-01' and '2020-01-31'";
        TypedQuery<Purchases> query = entityManager.createQuery(jpql,Purchases.class);
        List<Purchases> PurchaseList = query.getResultList();
        entityManager.getTransaction().commit();
        return PurchaseList;
    }
    public List<Purchases> retrieveFebruaryPurchaseList(){

        entityManager.getTransaction().begin();
        String jpql = "select p from Purchases p where p.purchaseDate between '2020-02-01' and '2020-01-28'";
        TypedQuery<Purchases> query = entityManager.createQuery(jpql,Purchases.class);
        List<Purchases> PurchaseList = query.getResultList();
        entityManager.getTransaction().commit();
        return PurchaseList;
    }

    public List<Purchases> retrieveMarchPurchaseList(){

        entityManager.getTransaction().begin();
        String jpql = "select p from Purchases p where p.purchaseDate between '2020-03-01' and '2020-03-31'";
        TypedQuery<Purchases> query = entityManager.createQuery(jpql,Purchases.class);
        List<Purchases> PurchaseList = query.getResultList();
        entityManager.getTransaction().commit();
        return PurchaseList;
    }
    public List<Purchases> retrieveAprilPurchaseList(){

        entityManager.getTransaction().begin();
        String jpql = "select p from Purchases p where p.purchaseDate between '2020-04-01' and '2020-04-30'";
        TypedQuery<Purchases> query = entityManager.createQuery(jpql,Purchases.class);
        List<Purchases> PurchaseList = query.getResultList();
        entityManager.getTransaction().commit();
        return PurchaseList;
    }

    public List<Purchases> retrieveMayPurchaseList(){

        entityManager.getTransaction().begin();
        String jpql = "select p from Purchases p where p.purchaseDate between '2020-05-01' and '2020-05-31'";
        TypedQuery<Purchases> query = entityManager.createQuery(jpql,Purchases.class);
        List<Purchases> PurchaseList = query.getResultList();
        entityManager.getTransaction().commit();
        return PurchaseList;
    }

    public List<Purchases> retrieveJunePurchaseList(){

        entityManager.getTransaction().begin();
        String jpql = "select p from Purchases p where p.purchaseDate between '2020-06-01' and '2020-06-30'";
        TypedQuery<Purchases> query = entityManager.createQuery(jpql,Purchases.class);
        List<Purchases> PurchaseList = query.getResultList();
        entityManager.getTransaction().commit();
        return PurchaseList;
    }

    public List<Purchases> retrieveJulyPurchaseList(){

        entityManager.getTransaction().begin();
        String jpql = "select p from Purchases p where p.purchaseDate between '2020-07-01' and '2020-07-31'";
        TypedQuery<Purchases> query = entityManager.createQuery(jpql,Purchases.class);
        List<Purchases> PurchaseList = query.getResultList();
        entityManager.getTransaction().commit();
        return PurchaseList;
    }

    public List<Purchases> retrieveAugustPurchaseList(){

        entityManager.getTransaction().begin();
        String jpql = "select p from Purchases p where p.purchaseDate between '2020-08-01' and '2020-08-31'";
        TypedQuery<Purchases> query = entityManager.createQuery(jpql,Purchases.class);
        List<Purchases> PurchaseList = query.getResultList();
        entityManager.getTransaction().commit();
        return PurchaseList;
    }

    public List<Purchases> retrieveSeptemberPurchaseList(){

        entityManager.getTransaction().begin();
        String jpql = "select p from Purchases p where p.purchaseDate between '2020-09-01' and '2020-09-30'";
        TypedQuery<Purchases> query = entityManager.createQuery(jpql,Purchases.class);
        List<Purchases> PurchaseList = query.getResultList();
        entityManager.getTransaction().commit();
        return PurchaseList;
    }

    public List<Purchases> retrieveOctoberPurchaseList(){

        entityManager.getTransaction().begin();
        String jpql = "select p from Purchases p where p.purchaseDate between '2020-10-01' and '2020-10-31'";
        TypedQuery<Purchases> query = entityManager.createQuery(jpql,Purchases.class);
        List<Purchases> PurchaseList = query.getResultList();
        entityManager.getTransaction().commit();
        return PurchaseList;
    }

    public List<Purchases> retrieveNovemberPurchaseList(){

        entityManager.getTransaction().begin();
        String jpql = "select p from Purchases p where p.purchaseDate between '2020-11-01' and '2020-11-30'";
        TypedQuery<Purchases> query = entityManager.createQuery(jpql,Purchases.class);
        List<Purchases> PurchaseList = query.getResultList();
        entityManager.getTransaction().commit();
        return PurchaseList;
    }

    public List<Purchases> retrieveDecemberPurchaseList(){

        entityManager.getTransaction().begin();
        String jpql = "select p from Purchases p where p.purchaseDate between '2020-12-01' and '2020-12-31'";
        TypedQuery<Purchases> query = entityManager.createQuery(jpql,Purchases.class);
        List<Purchases> PurchaseList = query.getResultList();
        entityManager.getTransaction().commit();
        return PurchaseList;
    }










}
