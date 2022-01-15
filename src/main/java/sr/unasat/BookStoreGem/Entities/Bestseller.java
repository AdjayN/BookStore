package sr.unasat.BookStoreGem.Entities;

public class Bestseller {

    private  String bookTitle;
    private int bookId;
    private int count;


    public Bestseller(String bookTitle, int bookId, int count) {
        this.bookTitle = bookTitle;
        this.bookId = bookId;
        this.count = count;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Bestseller{" +
                "bookTitle='" + bookTitle + '\'' +
                ", bookId=" + bookId +
                ", count=" + count +
                '}';
    }
}
