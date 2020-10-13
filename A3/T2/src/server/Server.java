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


/**
 * The Server program implements an javafx application that enables clients to communicate with to
 * calculate the total amount and monthly payment based on the user's sent loan data.
 *
 * @author Roshan Kharel
 * @version 1.0
 * @since 2020/10/05
 */
public class Server extends Application {
    TextArea textArea;
    int numberOfClients = 0;

    /**
     * This method gets called internally by JavaFX library
     *
     * @param primaryStage the window that gets displayed
     */
    @Override
    public void start(Stage primaryStage) {
        // setup up user interface layout
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

        // start the server
        establishSever();
    }

    /**
     * This method establishes a websocket communication channel on port 8080
     */
    private void establishSever() {
        new Thread(() -> {
            try {
                // Create a server socket
                ServerSocket serverSocket = new ServerSocket(8080);

                // display server started text message
                Platform.runLater(() -> writeTextArea(String.format(
                        "Server started at %s on port %d\n\n",
                        new Date(),
                        serverSocket.getLocalPort()
                )));

                // runs indefinitely
                while (true) {
                    // Listens for a connection to be made to this socket and accepts
                    // it. The method blocks until a connection is made.
                    Socket socket = serverSocket.accept();

                    // write to text area that a client is connected
                    writeTextArea(String.format(
                            "Client connected: %d\n",
                            ++numberOfClients
                    ));

                    // shift client server communication to a new thread
                    // this allows to handle multiple clients
                    Thread clientThread = new Thread(new ClintHandler(socket,
                            this::writeTextArea));
                    clientThread.setDaemon(true);
                    clientThread.start();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    /**
     * Method writes any text to the textarea
     *
     * @param text a text to be appended to textarea
     */
    protected synchronized void writeTextArea(String text) {
        textArea.appendText(text);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
