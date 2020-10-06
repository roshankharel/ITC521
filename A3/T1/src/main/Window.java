package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import main.repositories.StaffRepository;
import main.view.StaffPane;

import java.sql.SQLException;

public class Window extends Application {
    private final StaffRepository repository;
    private final Database database;

    public Window() throws SQLException, ClassNotFoundException {
        database = Database.getInstance();
        repository = new StaffRepository(database.getConnection());
    }

    @Override
    public void start(Stage primaryStage) {
        // create a scene
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


    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        launch(args);
        Database.getInstance().close();
    }
}
