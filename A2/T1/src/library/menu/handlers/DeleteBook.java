package library.menu.handlers;

import library.Application;
import library.Const;
import library.KeyboardInput;
import library.contracts.IMenuOption.IMenuOptionHandler;
import library.entities.Book;
import library.menu.Menu;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class DeleteBook extends SearchBook implements IMenuOptionHandler {
    public DeleteBook() {
        menu = new Menu(Const.Menu.Heading.SEARCH_BOOK_FOR_DELETION_MENU);
        registerMenuOptions(selection -> this.searchBy(selection, this::deleteBook));
    }

    private void deleteBook(ArrayList<Book> filteredBooks) {
        int[] options = IntStream.range(1, filteredBooks.size() + 1).toArray();
        int idx = KeyboardInput.getBookNumber(options) - 1;
        Book book = filteredBooks.get(idx);

        System.out.println("Success: The selected book has been deleted.");
        Application.getInstance().getBookRepository().deleteBook(book);
    }
}
