package library;

import library.contracts.IMenuOption;
import library.entities.Book;
import library.menu.Menu;
import library.menu.MenuOption;
import library.menu.handlers.*;
import library.repositories.BookRepository;
import java.util.ArrayList;

/**
 * The Application program is a library program that allows its
 * users to manage various books.
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

    /**
     * Internal method to run required methods before the application
     * prompt is visible
     */
    private void bootstrap() {
        loadBooks();
        registerMenuOptions();
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
        Application.getInstance().run();
    }
}
