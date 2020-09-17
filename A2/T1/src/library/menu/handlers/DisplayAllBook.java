package library.menu.handlers;

import library.Application;
import library.Const;
import library.contracts.IMenuOption.IMenuOptionHandler;
import library.entities.Book;

import java.util.ArrayList;

public class DisplayAllBook implements IMenuOptionHandler {
    @Override
    public void dispatch() {
        ArrayList<Book> books = Application.getInstance().getBookRepository().getBooks();

        if (books.size() == 0) {
            System.out.println(Const.Message.Info.NO_BOOKS);
            return;
        }

        System.out.println(Const.Message.ALL_BOOKS);

        for (Book book : books) {
            System.out.println("\n" + book.prettyStringRepresentation());
        }
    }
}
