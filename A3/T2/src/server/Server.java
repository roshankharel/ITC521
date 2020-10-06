package server;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;


public class Server extends Application {
    TextArea textArea;

    @Override
    public void start(Stage primaryStage) {
        textArea = new TextArea();
        textArea.setEditable(false);
        Pane root = new StackPane(textArea);

        textArea.prefWidthProperty().bind(root.prefWidthProperty());
        textArea.prefHeightProperty().bind(root.prefHeightProperty());
        root.setPadding(new Insets(4));

        primaryStage.setTitle("Loan Server");
        primaryStage.setScene(new Scene(root, 425, 275));
        primaryStage.setResizable(false);
        primaryStage.show();
        establishSever();
    }

    void establishSever() {
        new Thread(() -> {
            try {
                // Create a server socket
                ServerSocket serverSocket = new ServerSocket(8080);

                Platform.runLater(() -> textArea.appendText(String.format(
                        "Server started at %s on port %d\n",
                        new Date(),
                        serverSocket.getLocalPort()
                )));

                // Listen for a connection request
                Socket socket = serverSocket.accept();

                new Thread(new ClintHandler(socket, textArea)).start();


            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
