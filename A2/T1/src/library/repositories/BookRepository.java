package library.repositories;

import library.entities.Book;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;
/**
 * BookRepository
 * The BookRepository class provides methods to manage, query, and filter
 * books based on various attributes of Book object.
 *
 * @author Roshan Kharel
 */
public class BookRepository {
    protected ArrayList<Book> books;

    public BookRepository() {
        books = new ArrayList<>();
    }

    /**
     * Add book to the library
     *
     * @param book the Book
     */
    public void addBook(Book book) {
        books.add(book);
    }

    /**
     * Add books to the library
     *
     * @param books list of books
     */
    public void addBooks(ArrayList<Book> books) {
        for (Book book : books) {
            addBook(book);
        }
    }

    /**
     * Get all books from library after sorting
     *
     * @return list of books
     */
    public ArrayList<Book> getBooks() {
        Collections.sort(books);
        return books;
    }

    /**
     * Search all books in the library by its title
     *
     * @param title the title of the book
     * @return list of books
     */
    public ArrayList<Book> searchByTitle(final String title) {
        return books.stream()
                .filter(book -> book.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Search all books in the library by its author
     *
     * @param author the author of the book
     * @return list of books
     */
    public ArrayList<Book> searchByAuthor(final String author) {
        return books.stream()
                .filter(book -> book.getAuthor().toLowerCase().contains(author.toLowerCase()))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Search all books in the library by its published year
     *
     * @param year the published year of the book
     * @return list of books
     */
    public ArrayList<Book> searchByYearPublished(int year) {
        return books.stream()
                .filter(book -> book.getPublishedYear() == year)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Search all books in the library by its ISBN
     *
     * @param isbn the ISBN of the book
     * @return list of books
     */
    public ArrayList<Book> searchByISBN(BigInteger isbn) {
        return books.stream()
                .filter(book -> book.getIsbn().equals(isbn))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Delete a book from the library
     *
     * @param book the book to be removed from the library
     */
    public void deleteBook(Book book) {
        books = books.stream().filter(b -> b != book).collect(Collectors.toCollection(ArrayList::new));
    }
}
