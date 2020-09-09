package library.entities;

import library.Const;

import java.io.Serializable;
import java.math.BigInteger;

/** @author Roshan Kharel (11691041) */
public class Book implements Serializable, Comparable<Book> {
    protected String title;
    protected String author;
    protected int publishedYear; // Year of Publication;
    protected BigInteger isbn;

    /* no-argument default constructor */
    public Book() {}

    /**
     * @param title the title of the book
     * @param author the author of the book
     * @param publishedYear the published year of the book
     * @param isbn the standard number (ISBN) of the book
     */
    public Book(String title, String author, int publishedYear, BigInteger isbn) {
        this.title = title;
        this.author = author;
        this.publishedYear = publishedYear;
        this.isbn = isbn;
    }

    /** @return the title of the book */
    public String getTitle() {
        return title;
    }

    /** @param title the title of the book */
    public void setTitle(String title) {
        this.title = title;
    }

    /** @return the author of the book */
    public String getAuthor() {
        return author;
    }

    /** @param author the author of the book */
    public void setAuthor(String author) {
        this.author = author;
    }

    /** @return the published year of the book */
    public int getPublishedYear() {
        return publishedYear;
    }

    /** @param publishedYear the published year of the book */
    public void setPublishedYear(int publishedYear) {
        this.publishedYear = publishedYear;
    }

    public BigInteger getIsbn() {
        return isbn;
    }

    /** @param isbn the standard number (ISBN) of the book */
    public void setIsbn(BigInteger isbn) {
        this.isbn = isbn;
    }

    /**
     * Compares this object with the another Book for order. Returns a negative integer, zero, or a
     * positive integer
     *
     * @param book the Book object to be compared.
     * @return a negative integer, zero, or a positive integer as this book is less than, equal to, or
     *     greater than the specified book.
     */
    @Override
    public int compareTo(Book book) {
        // compare by title
        int compare = getTitle().compareTo(book.getTitle());

        if (compare != 0) return compare;

        // compare by author
        compare = getAuthor().compareTo(book.getAuthor());

        if (compare != 0) return compare;

        // compare by published year
        return getPublishedYear() - book.getPublishedYear();
    }

    @Override
    public String toString() {
        return String.format(
                "%s [title=%s, author=%s, publishedYear=%s, isbn=%s]",
                getClass(), getTitle(), getAuthor(), getPublishedYear(), getIsbn());
    }

    public String prettyStringRepresentation() {
        return String.format(
                Const.BOOK_REPRESENTATION, getTitle(), getAuthor(), getPublishedYear(), getIsbn());
    }
}
