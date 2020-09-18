package library;

import library.entities.Book;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Persistence
 * The Persistence class simply provides abstractions to
 * reads Book objects from the library.obj binary file
 *
 * @author Roshan Kharel
 */
public class Persistence {
    /**
     * Holds reference to the path of the library file
     */
    public static final Path PERSISTENCE_LOCATION = Paths.get("library.obj");

    /**
     * The method to read binary file and convert to ArrayList of Book objects
     *
     * @return ArrayList of Book objects
     */
    public static ArrayList<Book> readBooks() {
        // create an ArrayList to hold all the books
        ArrayList<Book> books = new ArrayList<>();

        try {
            // get the library binary file
            File libraryFile = makeOrGetFile();

            // if file is empty return books
            if(libraryFile.length() == 0) return books;

            // create the input stream from library file
            FileInputStream fileInputStream = new FileInputStream(libraryFile);
            // create a object input stream from the file input stream
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            // Read the stream as an generic Object instance
            Object readObject = objectInputStream.readObject();

            // check readObject is instance of ArrayList<?> generics
            if(readObject instanceof ArrayList<?>) {
                for (Object o : (ArrayList<?>) readObject) {
                    // check o is instance of Book
                    if(o instanceof Book) {
                        // append the book to the list
                        books.add((Book) o);
                    }
                }
            }

            // close the input stream to prevent memory leaks and release unneeded resources
            objectInputStream.close();

            return books;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * The method to write the serialized binary of the books list
     *
     * @param books list of books
     */
    public static void saveBooks(ArrayList<Book> books) throws IOException {
        // get the library binary file
        File libraryFile = makeOrGetFile();
        // create output stream from library file
        FileOutputStream fileOutputStream = new FileOutputStream(libraryFile);
        // create object output stream from the file output stream
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

        // write the whole arraylist of books on the object stream
        objectOutputStream.writeObject(books);
        // write any buffered output bytes and flush through to the underlying stream
        objectOutputStream.flush();

        // close the stream
        objectOutputStream.close();
    }

    /**
     * The method gets the library.obj bin file from the file system
     *
     * @return File library file
     * @throws IOException if library file does not exist
     *                     or unable to determine if file exists
     */
    protected static File makeOrGetFile() throws IOException {
        File libraryFile = PERSISTENCE_LOCATION.toFile();
        // check if file exists and is a file
        if(!libraryFile.exists() || !libraryFile.isFile()) {
            // create new file if points file does not exists
            libraryFile.createNewFile();
        }

        return libraryFile;
    }
}
