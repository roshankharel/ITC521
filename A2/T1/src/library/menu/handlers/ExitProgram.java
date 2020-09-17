package library.menu.handlers;

import library.Application;
import library.Const;
import library.KeyboardInput;
import library.Persistence;
import library.contracts.IMenuOption.IMenuOptionHandler;
import library.entities.Book;

import java.io.IOException;
import java.util.ArrayList;

public class ExitProgram implements IMenuOptionHandler {
    @Override
    public void dispatch() {
        try {
            System.out.println(Const.Message.Info.SAVING_BOOKS);

            ArrayList<Book> books = Application.getInstance().getBookRepository().getBooks();
            Persistence.saveBooks(books);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(Const.Message.Error.SAVING_BOOKS);
        }

        System.out.println(Const.Message.FAREWELL);
        // release input stream resources
        KeyboardInput.Keyboard.close();
        System.exit(0);
    }
}
