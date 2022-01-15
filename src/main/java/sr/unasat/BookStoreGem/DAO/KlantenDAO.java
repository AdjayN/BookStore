package sr.unasat.BookStoreGem.DAO;

import sr.unasat.BookStoreGem.Entities.Klanten;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class KlantenDAO {

    private EntityManager entityManager;
    EntityTransaction transaction = null;

    public KlantenDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Klanten create (Klanten klanten){

        try {
            //get a transaction
            transaction = entityManager.getTransaction();
            //begin transaction
            transaction.begin();
            //save the klantenobject
            entityManager.persist(klanten);

            //commit the transaction
            transaction.commit();
            System.out.println("new klant object has been added");
        }catch (Exception e){
            if(transaction!=null){
                transaction.rollback();
            }
            System.out.println("rollback transaction");
            e.printStackTrace();
        }
        return klanten;
    }

    public Klanten updateklanten(int id, Klanten klanten){
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            Klanten klant = entityManager.find(Klanten.class,id);
            klant.setNaam(klanten.getNaam());
            klant.setEmailaddres(klanten.getEmailaddres());

            entityManager.merge(klant);

            transaction.commit();
            System.out.println("klant record : " +id + " has been updated");
            return klant;

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

   public void delete(int id){
        try{
            transaction = entityManager.getTransaction();
            transaction.begin();

            // get klanten id
            Klanten klanten = entityManager.find(Klanten.class,id);

            entityManager.remove(klanten);

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

   public List<Klanten> retrievenKlantenlist(){

        entityManager.getTransaction().begin();
        String jpql = "select k from Klanten k";
       TypedQuery<Klanten> query = entityManager.createQuery(jpql,Klanten.class);
       List<Klanten> klantenList = query.getResultList();
       entityManager.getTransaction().commit();
       return klantenList;
   }

   //return een speciefiek klante object
    public Klanten selectKlantenById(int id){

        Klanten klanten = entityManager.find(Klanten.class,id);
        return  klanten;
    }




}
