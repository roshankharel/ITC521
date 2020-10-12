package main;

import com.mysql.cj.jdbc.exceptions.CommunicationsException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.repositories.StaffRepository;
import main.view.StaffPane;

import java.sql.SQLException;

/**
 * The StaffManager program implements an javafx application that
 * allows its user to manage their staff records. Each staff,
 * once added to the system, record is available throughout multiple
 * sessions. Its features searching staff by id, creating new staff,
 * and updating. For every operations, proper validation is employed
 * so as to prevent fetal application errors and also corresponding
 * error message is shown in human-readable way.
 *
 * @author Roshan Kharel
 * @version 1.0
 * @since 2020/09/23
 */
public class StaffManager extends Application {
    private final StaffRepository repository;
    private final Database database;

    /**
     * no-arguments default constructor
     */
    public StaffManager() throws SQLException, ClassNotFoundException {
        try {
            database = Database.getInstance();
            repository = new StaffRepository(database.getConnection());
        } catch (CommunicationsException e) {
            System.out.println("Error: Could not connect to the database.");

            throw e;
        }
    }

    /**
     * This method gets called internally by JavaFX library
     *
     * @param  primaryStage the window that gets displayed
     */
    @Override
    public void start(Stage primaryStage) {
        // create StaffPane as root pane
        Pane root = new StaffPane(repository);
        Scene scene = new Scene(root, Env.WINDOW_WIDTH, Env.WINDOW_HEIGHT);
        // set title of the main stage
        primaryStage.setTitle(Env.WINDOW_TITLE);
        // set the created scene
        primaryStage.setScene(scene);
        // set stage resizable
        primaryStage.setResizable(Env.WINDOW_IS_RESIZABLE);
        // make stage visible
        primaryStage.show();
    }


    /**
     * The main method which gets called while the application is launched
     *
     * @param  args arguments received while this application is run
     */
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        launch(args);
        // close database connection after the program is quit
        Database.getInstance().close();
    }
}
