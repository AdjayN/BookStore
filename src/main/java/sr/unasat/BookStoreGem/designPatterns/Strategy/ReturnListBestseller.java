package sr.unasat.BookStoreGem.designPatterns.Strategy;

import sr.unasat.BookStoreGem.DAO.BooksDAO;
import sr.unasat.BookStoreGem.Entities.Bestseller;
import sr.unasat.BookStoreGem.Entities.Purchases;
import sr.unasat.BookStoreGem.Entities.Purchases_book;
import sr.unasat.BookStoreGem.config.JPAConfiguration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReturnListBestseller implements Strategy {


    @Override
    public List<Bestseller> BESTSELLERLIST(List<Purchases_book> purchases_booksList) {

        System.out.println("---------------Best seller list-----------");

        Purchases_book[] purchases_booksArr = purchases_booksList.toArray(new Purchases_book[0]);

        int size = purchases_booksArr.length;
        boolean visited[] = new boolean[size];
        Arrays.fill(visited,false);

        List<Bestseller> bestsellerList = new ArrayList<>();

        for( int i = 0; i < size ; i++){


            if(visited[i] == true){
                continue;
            }


            int count = 1; //check
            for(int j = i + 1; j < size; j++){
                int id1 = purchases_booksArr[i].getBook_id();
                int id2 = purchases_booksArr[j].getBook_id();

                if(id1 == id2){
                    visited[j]= true;
                    count++;
                }

            }

            BooksDAO booksDAO = new BooksDAO(JPAConfiguration.getEntityManager());

            System.out.println("Book Title : " + booksDAO.selectBooksById(purchases_booksArr[i].getBook_id()).getTitel()
                    +" Book ID :"+ purchases_booksArr[i].getBook_id() + " " + " sold "
                    + count + " times");

            bestsellerList.add(new Bestseller(booksDAO.selectBooksById(purchases_booksArr[i].getBook_id()).getTitel(),
                    purchases_booksArr[i].getBook_id(), count));

        }

        System.out.println("----------------------------------");

        return bestsellerList;


    }

    @Override
    public int count(List<Purchases> purchasesList) {
        return 0;
    }

    @Override
    public int omzetAmountPerMaand(List<Purchases> purchases) {
        return 0;
    }
}
