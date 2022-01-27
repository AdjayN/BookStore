package sr.unasat.BookStoreGem.services;

import sr.unasat.BookStoreGem.DAO.AuthorDAO;
import sr.unasat.BookStoreGem.DAO.BooksDAO;
import sr.unasat.BookStoreGem.DAO.KlantenDAO;
import sr.unasat.BookStoreGem.Entities.Author;
import sr.unasat.BookStoreGem.Entities.Books;
import sr.unasat.BookStoreGem.Entities.Klanten;
import sr.unasat.BookStoreGem.config.JPAConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookService {

    private Scanner scanner;
    private Books book;
    private Author author;
    BooksDAO booksDAO;
    AuthorDAO authorDAO;

    public BookService() {
        this.scanner = new Scanner(System.in);
        this.booksDAO = new BooksDAO(JPAConfiguration.getEntityManager());
        this.authorDAO = new AuthorDAO(JPAConfiguration.getEntityManager());
    }


    public void booksMenuService() {
        boolean exit = true;

        while (exit) {

            String klantenHeader = "\n-----Books----\n";

            System.out.print(klantenHeader + "\nMenu: \n"
                    + 1 + ". Select a Book.\n"
                    + 2 + ". Add Book.\n"
                    + 3 + ". Add Book with author id.\n"
                    + 4 + ". Update Book.\n"
                    + 5 + ". Delete Book.\n"
                    + 6 + ". Select all Books.\n"
                    + 7 + ". Exit\n");

            int selectedOption = scanner.nextInt();

            switch (selectedOption) {
                case 1:
                    System.out.println("Select on");
                    int bookId = getBookId();
                    Books books = getBook(bookId);
                    System.out.println(books);
                    break;
                case 2:
                    Books books1 = createBooks();
                    addBook(books1);
                    break;
                case 3:
                    Books booksWithAuthorIds = createBooksWithAuthorIds();
                    addBookWithAuthorId(booksWithAuthorIds);
                    break;
                case 4:
                    System.out.println("Update book");
                    System.out.println("Enter old book id");
                    int oldBookId = scanner.nextInt();
                    Books books2 = getUpdateBookInfo();
                    updateBook(oldBookId, books2);
                    break;
                case 5:
                    System.out.println("Delete");
                    int deleteBookId = getBookId();
                    deleteBook(deleteBookId);
                    break;
                case 6:
                    System.out.println("Select all");
                    System.out.println("Select all boeken");
                    List<Books> bookList = getBooksList();
                    System.out.println(bookList);
                    break;
                case 7:
                    exit = false;
                    break;
                default:
                    System.out.print("\n----Invalid option selected!----\n");
            }
            ;

        }
    }

    private Books createBooksWithAuthorIds() {
        Books books = new Books();

        System.out.println("\n Add a book:\n");

        scanner.nextLine();
        System.out.println("Enter  Titel ");
        String Booktitel = scanner.nextLine();
        books.setTitel(Booktitel);

        System.out.println("Enter book isbn");
        String BookIsbn = scanner.nextLine();
        books.setIsbn(BookIsbn);

        System.out.println("Enter  prijs ");
        int BookPrijs = scanner.nextInt();
        books.setPrijs(BookPrijs);

        System.out.println("Enter  GereserveerdAantal ");
        int BookGereserveerdAantal = scanner.nextInt();
        books.setGereserveerd_aantal(BookGereserveerdAantal);

        System.out.println("Enter  Aantal ");
        int AantalId = scanner.nextInt();
        books.setAantal(AantalId);

        List<Author> authorList = new ArrayList<>();
        boolean authorSelected = false;
        while (!authorSelected) {

            System.out.print(
                    + 1 + ". Add a Author .\n"
                            + 2 + ". Finish adding Authors\n");
            int selectedOption = scanner.nextInt();
            switch(selectedOption){
                case 1:
                    Author author = new Author();
                    System.out.println("Enter a author id");
                    int authorid = scanner.nextInt();
                    author.setIdAuthor(authorid);
                    authorList.add(author);
                    break;
                case 2:
                    authorSelected = true;
                    break;
                default:
                    System.out.print("\n----Invalid option selected!----\n");
            };
            books.setAuthorList(authorList);

        }

        return books;

//        Author author1 = new Author();
//         author1.setIdAuthor(13);
//
//         Author author2 = new Author();
//         author2.setIdAuthor(14);
//
//         Author author3 = new Author();
//         author3.setIdAuthor(15);
//
//
//        return books;
    }


        private Books createBooks () {

            Books books = new Books();

            System.out.println("\n Add a book:\n");

            scanner.nextLine();
            System.out.println("Enter  Titel ");
            String Booktitel = scanner.nextLine();
            books.setTitel(Booktitel);

            System.out.println("Enter book isbn");
            String BookIsbn = scanner.nextLine();
            books.setIsbn(BookIsbn);

            System.out.println("Enter  prijs ");
            int BookPrijs = scanner.nextInt();
            books.setPrijs(BookPrijs);

            System.out.println("Enter  GereserveerdAantal ");
            int BookGereserveerdAantal = scanner.nextInt();
            books.setGereserveerd_aantal(BookGereserveerdAantal);

            System.out.println("Enter  Aantal ");
            int AantalId = scanner.nextInt();
            books.setAantal(AantalId);

            List<Author> authorList = new ArrayList<>();
            boolean authorSelected = false;
            while(!authorSelected) {
                System.out.println(
                        +1 + "Add a Author. \n"
                                + 2 + "Finish adding Authors\n");
                int selectOption = scanner.nextInt();
                switch (selectOption) {
                    case 1:
                        Author author = new Author();
                        scanner.nextLine();
                        System.out.println("Enter a author Name");
                        String AuthorName = scanner.nextLine();
                        author.setNaam(AuthorName);

                        System.out.println("Enter a author Achternaam");
                        String AuthorAchternaam = scanner.nextLine();
                        author.setAchternaam(AuthorAchternaam);
                        authorList.add(author);
                        break;
                    case 2:
                        authorSelected = true;
                        break;
                    default:
                        System.out.println("\n---- Invalid option selected!----");

                };
                books.setAuthorList(authorList);
            }
            return books;
        }

        public List<Books> getBooksList () {

            List<Books> booksList = booksDAO.retrieveBooklist();
            return booksList;

        }

        public Books getBook ( int id){

            Books books = booksDAO.selectBooksById(id);
            return books;
        }

        private int getBookId () {

            System.out.println("\nEnter book id:\n");
            int booksId = scanner.nextInt();

            return booksId;
        }

        // add new book record to database
        public Books addBook (Books books){
            return booksDAO.create(books);
        }


        // add book with authorID
        public Books addBookWithAuthorId (Books books){

            List<Author> authorListWithID = books.getAuthorList();
            List<Author> authorList = new ArrayList<>();
            if (authorListWithID.size() == 1) {

                Author author1WithId = authorListWithID.get(0);
                int author1Id = author1WithId.getIdAuthor();
                Author author1 = authorDAO.selectAuthorById(author1Id);
                authorList.add(author1);


            }
            if (authorListWithID.size() == 2) {

                Author author1WithId = authorListWithID.get(0);
                int author1Id = author1WithId.getIdAuthor();
                Author author1 = authorDAO.selectAuthorById(author1Id);
                authorList.add(author1);

                Author author2WithId = authorListWithID.get(1);
                int author2Id = author2WithId.getIdAuthor();
                Author author2 = authorDAO.selectAuthorById(author2Id);
                authorList.add(author2);

            }
            if (authorListWithID.size() == 3) {

                Author author1WithId = authorListWithID.get(0);
                int author1Id = author1WithId.getIdAuthor();
                Author author1 = authorDAO.selectAuthorById(author1Id);
                authorList.add(author1);

                Author author2WithId = authorListWithID.get(1);
                int author2Id = author2WithId.getIdAuthor();
                Author author2 = authorDAO.selectAuthorById(author2Id);
                authorList.add(author2);

                Author author3WithId = authorListWithID.get(2);
                int author3Id = author3WithId.getIdAuthor();
                Author author3 = authorDAO.selectAuthorById(author3Id);
                authorList.add(author3);

            }

            books.setAuthorList(authorList);

            return booksDAO.create(books);
        }


        // Update book record from database
        public Books updateBook ( int id, Books books){
            return booksDAO.updatebooks(id, books);

        }
        private Books getUpdateBookInfo () {

            Books books = new Books();

            scanner.nextLine();
            System.out.println("Enter new book id");
            int newBookId = scanner.nextInt();
            book.setIdbook(newBookId);

            System.out.println("Enter new book isbn");
            String newBookIsbn = scanner.nextLine();
            book.setIsbn(newBookIsbn);

            System.out.println("Enter new Titel ");
            String newBooktitel = scanner.nextLine();
            book.setTitel(newBooktitel);


            System.out.println("Enter new prijs ");
            int newBookPrijs = scanner.nextInt();
            book.setPrijs(newBookPrijs);


            System.out.println("Enter new GereserveerdAantal ");
            int newBookGereserveerdAantal = scanner.nextInt();
            book.setGereserveerd_aantal(newBookGereserveerdAantal);

            System.out.println("Enter new Aantal ");
            int newAantalId = scanner.nextInt();
            book.setIdbook(newAantalId);

            return books;
        }
        // Delete book record from databse
        public void deleteBook ( int id){
            booksDAO.delete(id);
        }


    }


