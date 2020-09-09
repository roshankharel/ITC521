package library;

import library.entities.Book;
import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Persistence {
    public static final Path PersistenceLocation = Paths.get("library.obj");

    public static ArrayList<Book> loadBooks() {
        try {
            File libraryFile = makeOrGetFile();

            if(libraryFile.length() == 0) return null;

            FileInputStream fileInputStream = new FileInputStream(libraryFile);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            ArrayList<Book> books = (ArrayList<Book>) objectInputStream.readObject();

            objectInputStream.close();

            return books;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void saveBooks(ArrayList<Book> books) throws IOException {
        File libraryFile = makeOrGetFile();
        FileOutputStream fileOutputStream = new FileOutputStream(libraryFile);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

        objectOutputStream.writeObject(books);
        objectOutputStream.flush();
        objectOutputStream.close();
    }

    protected static File makeOrGetFile() throws IOException {
        if(!Files.exists(PersistenceLocation)) {
            Files.createFile(PersistenceLocation);
        }

        return PersistenceLocation.toFile();
    }
}
