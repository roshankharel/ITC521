package library;

import library.contracts.IMenuOption;
import library.entities.Book;
import library.menu.Menu;
import library.menu.MenuOption;
import library.menu.handlers.*;
import library.repositories.BookRepository;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

/** @author Roshan Kharel (11691041) */
public class Application {
    private static Application instance;

    protected Menu menu;
    protected BookRepository bookRepository;
    public static final Scanner Keyboard = new Scanner(System.in);

    /** no-arguments default constructor */
    private Application() {
        bookRepository = new BookRepository();
        menu = new Menu(Const.Menu.Heading.Application);
    }

    public static Application ctx() {
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

    public Application bootstrap() {
        loadBooks();
        registerMenuOptions();

//        fillData();

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

    public void run() {
        while (true) {
            System.out.printf("\n%s\n\n", Const.GREETING);
            menu.display().awaitOptionSelection();
        }
    }

    public BookRepository getBookRepository() {
        return bookRepository;
    }

    public void loadBooks() {
        ArrayList<Book> books = Persistence.readBooks();

        if (books != null) bookRepository.addBooks(books);
    }

    /** @param args program arguments */
    public static void main(String[] args) {
        Application.ctx().bootstrap().run();
    }
}
