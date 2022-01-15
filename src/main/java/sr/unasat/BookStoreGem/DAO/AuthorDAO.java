package sr.unasat.BookStoreGem.DAO;

import sr.unasat.BookStoreGem.Entities.Author;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class AuthorDAO {

    private EntityManager entityManager;
    EntityTransaction transaction = null;

    public AuthorDAO(EntityManager entityManager){
        this.entityManager = entityManager; }

    public void create (Author author){

        try {
            //get a transaction
            transaction = entityManager.getTransaction();
            //begin transaction
            transaction.begin();
            //save the authorObject
            entityManager.persist(author);

            //commit the transaction
            transaction.commit();
            System.out.println("new author object has been added");
        }catch (Exception e){
            if(transaction!=null){
                transaction.rollback();
            }
            System.out.println("rollback transaction");
            e.printStackTrace();
        }
    }

    public void updateAuthor(int id, String name, String achtenaam){
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            Author author = entityManager.find(Author.class,id);
            author.setNaam(name);
            author.setAchternaam(achtenaam);

            entityManager.merge(author);

            transaction.commit();
            System.out.println("record : " +id + " has been updated");

        }catch ( Exception e) {
            // if there are any exceptions, roll back the changes
            if(transaction != null){
                transaction.rollback();
            }
            //print the exception
            e.printStackTrace();
        }
    }

    public void delete(int id){
        try{
            transaction = entityManager.getTransaction();
            transaction.begin();

            // get Author id
            Author author = entityManager.find(Author.class,id);

            entityManager.remove(author);

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

    public List<Author> retrieveAuthorlist(){

        entityManager.getTransaction().begin();
        String jpql = "select a from Author a";
        TypedQuery<Author> query = entityManager.createQuery(jpql,Author.class);
        List<Author> authorList = query.getResultList();
        entityManager.getTransaction().commit();
        return authorList;
    }

    public Author selectAuthorById(int id){
        Author author = entityManager.find(Author.class,id);
        return author;
    }



}
