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
    int numberOfClients = 0;

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

                Platform.runLater(() -> writeTextArea(String.format(
                        "Server started at %s on port %d\n\n",
                        new Date(),
                        serverSocket.getLocalPort()
                )));

                while (true) {
                    // Listen for a connection request
                    Socket socket = serverSocket.accept();

                    writeTextArea(String.format(
                            "Client connected: %d\n",
                            ++numberOfClients
                    ));

                    new Thread(new ClintHandler(socket, this::writeTextArea)).start();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    protected synchronized void writeTextArea(String text) {
        textArea.appendText(text);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
