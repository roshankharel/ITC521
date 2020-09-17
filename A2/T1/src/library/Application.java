package library;

import library.contracts.IMenuOption;
import library.entities.Book;
import library.menu.Menu;
import library.menu.MenuOption;
import library.menu.handlers.*;
import library.repositories.BookRepository;

import java.math.BigInteger;
import java.util.ArrayList;

/**
 * The Application program is a library program that allows its
 * users to manage various unlimited books.
 *
 * @author Roshan Kharel
 * @version 1.0
 * @since 2020/09/15
 */
public class Application {
    private static Application instance;

    protected Menu menu;
    protected BookRepository bookRepository;

    /**
     * no-arguments default private constructor, follows singleton pattern
     */
    private Application() {
        bookRepository = new BookRepository();
        menu = new Menu(Const.Menu.Heading.MAIN_MENU);

        bootstrap();
    }

    /**
     * The method to let access to same instance object of this class
     *
     * @return Application instance
     */
    public static Application getInstance() {
        if(instance == null) instance = new Application();

        return instance;
    }

    @SuppressWarnings("unused")
    private void fillData() {
        bookRepository.addBook(
                new Book(
                        "A day in the country",
                        "Anne Preston",
                        2001,
                        BigInteger.valueOf(Long.parseLong("7488796045"))));
        bookRepository.addBook(
                new Book(
                        "Last light", "Alex Sparrow", 2007, BigInteger.valueOf(Long.parseLong("991145598"))));
        bookRepository.addBook(
                new Book(
                        "Nineteen eighty four",
                        "George Orwell",
                        1984,
                        BigInteger.valueOf(Long.parseLong("1991145592"))));
        bookRepository.addBook(
                new Book(
                        "Nineteen eighty four",
                        "George Orwell",
                        1986,
                        BigInteger.valueOf(Long.parseLong("2991145592"))));
        bookRepository.addBook(
                new Book(
                        "Nineteen eighty four",
                        "Mary Owen",
                        1986,
                        BigInteger.valueOf(Long.parseLong("1676957444"))));
    }

    /**
     * Internal method to run required methods before the application
     * prompt is visible
     */
    private Application bootstrap() {
        loadBooks();
        registerMenuOptions();

        return this;
    }

    private void registerMenuOptions() {
        IMenuOption[] options =
            new IMenuOption[] {
                new MenuOption(
                    Const.Menu.Order.ADD_BOOK,
                    Const.Menu.Description.ADD_BOOK,
                    new AddBook()
                ),
                new MenuOption(
                    Const.Menu.Order.DELETE_BOOK,
                    Const.Menu.Description.DELETE_BOOK,
                    new DeleteBook()
                ),
                new MenuOption(
                    Const.Menu.Order.SEARCH_BOOKS,
                    Const.Menu.Description.SEARCH_BOOKS,
                    new SearchBook()
                ),
                new MenuOption(
                    Const.Menu.Order.DISPLAY_ALL_BOOKS,
                    Const.Menu.Description.DISPLAY_ALL_BOOKS,
                    new DisplayAllBook()
                ),
                new MenuOption(
                    Const.Menu.Order.EXIT,
                    Const.Menu.Description.EXIT,
                    new ExitProgram()
                ),
            };

        menu.addOptions(options);
    }

    /**
     * The main application loop that keeps the application running
     */
    public void run() {
        // runs indefinitely until exit signal is received
        while (true) {
            System.out.printf("\n%s\n\n", Const.GREETING);
            menu.display().awaitOptionSelection();
        }
    }

    /**
     * Get book repository
     *
     * @return BookRepository instance
     */
    public BookRepository getBookRepository() {
        return bookRepository;
    }

    /**
     * Method to load books from file persistence to runtime memory
     */
    public void loadBooks() {
        ArrayList<Book> books = Persistence.readBooks();

        if (books != null) bookRepository.addBooks(books);
    }

    /** @param args program arguments */
    public static void main(String[] args) {
        Application.getInstance().bootstrap().run();
    }
}
