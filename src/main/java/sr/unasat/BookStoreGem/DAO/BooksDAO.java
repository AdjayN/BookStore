package sr.unasat.BookStoreGem.DAO;

import sr.unasat.BookStoreGem.Entities.Books;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class BooksDAO {

    private EntityManager entityManager;
    EntityTransaction transaction = null;

    public BooksDAO(EntityManager entityManager){ this.entityManager = entityManager; }

    public Books create (Books books){

        try {
            //get a transaction
            transaction = entityManager.getTransaction();
            //begin transaction
            transaction.begin();
            //save the booksobject
            entityManager.persist(books);

            //commit the transaction
            transaction.commit();
            System.out.println("new books object has been added");
        }catch (Exception e){
            if (transaction!=null){
                transaction.rollback();
            }
            System.out.println("rollback transaction");
            e.printStackTrace();
        }
        return books;
    }

    public Books updatebooks(int id, Books books){
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            Books book = entityManager.find(Books.class,id);
            book.setIsbn(books.getIsbn());
            book.setTitel(books.getTitel());


            entityManager.merge(book);

            transaction.commit();
            System.out.println("record : " +id + " has been updated");

            return  book;

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

            // get book id
            Books books = entityManager.find(Books.class,id);

            entityManager.remove(books);

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

    public List<Books> retrieveBooklist(){

        entityManager.getTransaction().begin();
        String jpql = "select b from Books b";
        TypedQuery<Books> query = entityManager.createQuery(jpql,Books.class);
        List<Books> booksList = query.getResultList();
        entityManager.getTransaction().commit();
        return booksList;
    }

    //returned een speciefieke bookbject
    public Books selectBooksById(int id){
        Books books = entityManager.find(Books.class,id);
        return books;
    }

    public Books updateBooksReserveringAantal(int id, int gereserveerd_aantal){
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            Books book = entityManager.find(Books.class,id);
            if(book.getGereserveerd_aantal() < book.getAantal()){
                book.setGereserveerd_aantal(book.getGereserveerd_aantal() + gereserveerd_aantal);
                entityManager.merge(book);
                transaction.commit();
                System.out.println("gereserveerd_aantal book : " +id + " has been updated");
                return book;
            }else {
                System.out.println("Het gekozen boek is niet meer in voorraad");
            }


        }catch ( Exception e) {
            // if there are any exceptions, roll back the changes
            if(transaction != null){
                transaction.rollback();
            }
            //pritn the exception
            e.printStackTrace();
        }
        return null;
    }

    public Books updateBooksAantal(int bookId){
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            Books book = entityManager.find(Books.class,bookId);
            if(book.getAantal()>0){
                book.setAantal(book.getAantal() - 1);
                entityManager.merge(book);
                transaction.commit();
                System.out.println("Aantal book : " + bookId + " has been updated");
            }else{
                System.out.println("Het gekozen boek is niet meer in voorraad");
            }

            return book;

        }catch ( Exception e) {
            // if there are any exceptions, roll back the changes
            if(transaction != null){
                transaction.rollback();
            }
            //pritn the exception
            e.printStackTrace();
        }

        return null;
    }




}
