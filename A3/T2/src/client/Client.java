package client;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * The Client program implements an javafx application that
 * allows its user to communicate with the server application
 * utilizing websocket. This class is responsible for displaying
 * form fields for user to send loan data to the server.
 *
 * @author Roshan Kharel
 * @version 1.0
 * @since 2020/10/05
 */
public class Client extends Application {
    // IO streams
    DataOutputStream toServer = null;
    DataInputStream fromServer = null;

    // text area to show the loan result
    TextArea result = new TextArea();

    // submit button
    Button submitButton = new Button("Submit");

    // form fields for load data - user input
    TextField tfAnnualInterestRate = new TextField();
    TextField tfNumberOfYears = new TextField();
    TextField tfLoanAmount = new TextField();

    /**
     * This method gets called internally by JavaFX library
     *
     * @param  primaryStage the window that gets displayed
     */
    @Override
    public void start(Stage primaryStage) {
        // create a scene
        Scene scene = new Scene(getPane(), 380, 310);

        // set title of the main stage
        primaryStage.setTitle("Loan Client");
        // set the created scene
        primaryStage.setScene(scene);
        // set stage resizable
        primaryStage.setResizable(false);
        // make stage visible
        primaryStage.show();

        // use java fx inbuilt thread to asynchronously connect with the server
        // this solves the problem of UI freezing if run synchronously
        Platform.runLater(() -> {
            try {
                connectToServer();
                // show connected information after connecting to server
                result.appendText("Connected to Server.\n");
            } catch (IOException e) {
                // there was an error connecting
                result.appendText(e.getMessage() + "\n");
            }
        });

        listenEvents();
    }

    /**
     * Internal method to build the UI pane for the user to interact with
     */
    private Pane getPane() {
        result.setEditable(false);

        // grid pane
        GridPane grid = new GridPane();

        // labels for input
        Label annualInterestRateLbl = new Label("Annual Interest Rate");
        Label numberOfYearsLbl = new Label("Number of Years");
        Label loanAmountLbl = new Label("Loan Amount");

        // labels and form input placement in the grid
        grid.add(annualInterestRateLbl, 0, 0);
        grid.add(tfAnnualInterestRate, 1, 0);
        grid.add(numberOfYearsLbl, 0, 1);
        grid.add(tfNumberOfYears, 1, 1);
        grid.add(loanAmountLbl, 0, 2);
        grid.add(tfLoanAmount, 1, 2);
        grid.setVgap(8);
        grid.setHgap(8);

        // submit button placement
        HBox hBox = new HBox(submitButton);
        hBox.setPrefHeight(grid.getPrefHeight());
        hBox.setAlignment(Pos.CENTER);
        hBox.setPadding(new Insets(12));

        // grid placement
        HBox top = new HBox(grid, hBox);
        top.setPadding(new Insets(12));
        top.setAlignment(Pos.CENTER);

        // result textarea placement
        StackPane bottom = new StackPane(result);
        bottom.setPadding(new Insets(4));

        BorderPane pane = new BorderPane();
        pane.setTop(top);
        pane.setBottom(bottom);

        return pane;
    }

    /**
     * Internal method to listen for all kinds of system events
     */
    private void listenEvents() {
        // listen for mouse click on submit button
        submitButton.setOnMouseClicked(event -> {
            // check input validity
            if(!validateInput()) return;

            // check data output stream to server is initialized
            if (toServer == null) return;

            // variables declaration
            double rate, year, amount, monthlyPayment, totalPayment;

            try {
                // pares to double
                rate = Double.parseDouble(tfAnnualInterestRate.getText().trim());
                year = Double.parseDouble(tfNumberOfYears.getText().trim());
                amount = Double.parseDouble(tfLoanAmount.getText().trim());

                // Send loan date to server one after another
                toServer.writeDouble(rate);
                toServer.writeDouble(year);
                toServer.writeDouble(amount);

                // force any buffered output bytes to be written out to the stream
                toServer.flush();

                // calculated loan data received from server
                monthlyPayment = fromServer.readDouble();
                totalPayment = fromServer.readDouble();

                // Display to the text area
                result.appendText(String.format(
                        "Annual Interest Rate: %f\n" +
                                "Number of Years: %f\n" +
                                "Loan Amount: %f\n" +
                                "Monthly Payment: %f\n" +
                                "Total Payment: %f\n\n",
                        rate, year, amount, monthlyPayment, totalPayment
                ));

            } catch (IOException e) {
                result.appendText(e.getMessage());
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Method connects to the server application
     *
     * @throws IOException if server is unreachable
     */
    private void connectToServer() throws IOException {
        // Creates a stream socket and connects it to the specified port
        // number on the named host.
        Socket socket = new Socket("127.0.0.1", 8080);
        // Creates a DataInputStream that uses the specified underlying InputStream.
        fromServer = new DataInputStream(socket.getInputStream());
        // Creates a DataOutputStream that uses the specified underlying OutputStream.
        toServer = new DataOutputStream(socket.getOutputStream());
    }

    /**
     * Method checks all input fields and displays error message if validation fails
     *
     * @return validity of the form fields
     */
    private boolean validateInput() {
        boolean isValid = true;

        // check annual interest rate
        try {
            double rate = Double.parseDouble(tfAnnualInterestRate.getText().trim());

            // cannot be negative
            if(rate < 0) {
                isValid = false;
                result.appendText("Error: annual interest rate cannot be negative number.\n");
            }

        } catch (NumberFormatException e) { // not a number
            isValid = false;
            result.appendText("Error: annual interest rate must be a valid number.\n");
        }

        // check number of years
        try {
            double years = Double.parseDouble(tfNumberOfYears.getText().trim());

            // cannot be negative
            if(years < 0) {
                isValid = false;
                // display error message
                result.appendText("Error: number of years cannot be negative number.\n");
            }

        } catch (NumberFormatException e) { // not a number
            isValid = false;
            // display error message
            result.appendText("Error: number of years must be a valid number.\n");
        }

        // check loan amount
        try {
            double amount = Double.parseDouble(tfLoanAmount.getText().trim());

            // cannot be negative
            if(amount < 0) {
                isValid = false;
                // display error message
                result.appendText("Error: loan amount cannot be negative number.\n");
            }

        } catch (NumberFormatException e) { // not a number
            isValid = false;
            result.appendText("Error: loan amount must be a valid number.\n");
        }

        return isValid;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
