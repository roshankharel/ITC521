package library.menu.handlers;

import library.Application;
import library.Const;
import library.KeyboardInput;
import library.contracts.IMenuOption.IMenuOptionHandler;
import library.entities.Book;

import java.math.BigInteger;

public class AddBook implements IMenuOptionHandler {
    @Override
    public void dispatch() {
        String title = KeyboardInput.getTitle();
        String author = KeyboardInput.getAuthor();
        int publishedYear = KeyboardInput.getYear();
        BigInteger isbn = KeyboardInput.getISBN();

        Book book = new Book(title, author, publishedYear, isbn);
        Application.ctx().getBookRepository().addBook(book);

        System.out.println(Const.Message.Success.ADD_BOOK);
    }
}
