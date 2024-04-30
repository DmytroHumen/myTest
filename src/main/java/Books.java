public class Books {

    private String bookName;
    private String author;
    private String price;
    private boolean isBestseller;

    public Books(String bookName, String author, String price, boolean isBestseller) {
        this.bookName = bookName;
        this.author = author;
        this.price = price;
        this.isBestseller = isBestseller;
    }

    public String getBookName() {
        return bookName;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookName='" + bookName + '\'' +
                ", author='" + author + '\'' +
                ", price='" + price + '\'' +
                ", isBestseller=" + isBestseller +
                '}';
    }
}