package library.menu.handlers;

import library.Application;
import library.Const;
import library.KeyboardInput;
import library.contracts.IMenuOption;
import library.contracts.IMenuOption.IMenuOptionHandler;
import library.entities.Book;
import library.menu.Menu;
import library.menu.MenuOption;
import library.repositories.BookRepository;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.function.Consumer;

public class SearchBook implements IMenuOptionHandler {
    protected String query;
    protected String searchByField;
    protected Menu menu;

    public SearchBook() {
        menu = new Menu(Const.Menu.Heading.SearchBook);
        registerMenuOptions(this::searchBy);
    }

    @Override
    public void dispatch() {
        menu.display().awaitOptionSelection();
    }

    protected void registerMenuOptions(Consumer<Integer> onSelection) {
        IMenuOption[] options =
                new IMenuOption[] {
                        new MenuOption(
                                Const.Menu.Order.CANCEL_ACTION, Const.Menu.Description.CANCEL_ACTION, null),
                        new MenuOption(
                                Const.Menu.Order.BY_TITLE,
                                Const.Menu.Description.BY_TITLE,
                                () -> onSelection.accept(Const.Menu.Order.BY_TITLE)),
                        new MenuOption(
                                Const.Menu.Order.BY_AUTHOR,
                                Const.Menu.Description.BY_AUTHOR,
                                () -> onSelection.accept(Const.Menu.Order.BY_AUTHOR)),
                        new MenuOption(
                                Const.Menu.Order.BY_YEAR,
                                Const.Menu.Description.BY_YEAR,
                                () -> onSelection.accept(Const.Menu.Order.BY_YEAR)),
                        new MenuOption(
                                Const.Menu.Order.BY_ISBN,
                                Const.Menu.Description.BY_ISBN,
                                () -> onSelection.accept(Const.Menu.Order.BY_ISBN)),
                };

        menu.addOptions(options);
    }

    protected void searchBy(int searchBy) {
        this.searchBy(searchBy, null);
    }

    protected void searchBy(int searchBy, Consumer<ArrayList<Book>> next) {
        ArrayList<Book> books = searchBooks(Application.ctx().getBookRepository(), searchBy);

        if (books.size() == 0) {
            noMatchFound();
            return;
        }

        displayBooks(books, next != null);

        if (next != null) next.accept(books);
    }

    protected ArrayList<Book> searchBooks(BookRepository repository, int searchBy) {
        ArrayList<Book> books;

        switch (searchBy) {
            case Const.Menu.Order.BY_ISBN:
                searchByField = "ISBN";
                BigInteger isbn = KeyboardInput.getISBN();
                query = String.valueOf(isbn);
                books = repository.searchByISBN(isbn);
                break;
            case Const.Menu.Order.BY_AUTHOR:
                searchByField = "Author";
                String author = query = KeyboardInput.getAuthor();
                books = repository.searchByAuthor(author);
                break;
            case Const.Menu.Order.BY_YEAR:
                searchByField = "Published Year";
                int publishedYear = KeyboardInput.getYear();
                query = String.valueOf(publishedYear);
                books = repository.searchByYearPublished(publishedYear);
                break;
            case Const.Menu.Order.BY_TITLE:
                searchByField = "Title";
                String title = query = KeyboardInput.getTitle();
                books = repository.searchByTitle(title);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + searchBy);
        }

        return books;
    }

    protected void displayBooks(ArrayList<Book> books) {
        displayBooks(books, false);
    }

    protected void displayBooks(ArrayList<Book> books, boolean numbered) {
        int idx = 1;

        System.out.printf(
                "\nThese are all the books that matched the search criteria '%s' on the field '%s'.\n",
                query, searchByField);

        for (Book book : books) {
            System.out.println(
                    (numbered ? ("\nBook #" + idx++ + "\n") : "\n") + book.prettyStringRepresentation());
        }
    }

    protected void noMatchFound() {
        System.out.printf(
                "\nInfo: The search criteria '%s' does not match any book on the field '%s'.\n",
                query, searchByField);
    }
}
