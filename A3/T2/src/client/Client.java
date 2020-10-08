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

public class Client extends Application {
    // IO streams
    DataOutputStream toServer = null;
    DataInputStream fromServer = null;

    TextArea result = new TextArea();
    Button submitButton = new Button("Submit");
    TextField tfAnnualInterestRate = new TextField();
    TextField tfNumberOfYears = new TextField();
    TextField tfLoanAmount = new TextField();

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

        Platform.runLater(() -> {
            try {
                connectToServer();
                result.appendText("Connected to Server.\n");
            } catch (IOException e) {
                result.appendText(e.getMessage() + "\n");
            }
        });

        listenEvents();
    }

    Pane getPane() {
        result.setEditable(false);

        GridPane grid = new GridPane();
        Label annualInterestRateLbl = new Label("Annual Interest Rate");
        Label numberOfYearsLbl = new Label("Number of Years");
        Label loanAmountLbl = new Label("Loan Amount");

        grid.add(annualInterestRateLbl, 0, 0);
        grid.add(tfAnnualInterestRate, 1, 0);
        grid.add(numberOfYearsLbl, 0, 1);
        grid.add(tfNumberOfYears, 1, 1);
        grid.add(loanAmountLbl, 0, 2);
        grid.add(tfLoanAmount, 1, 2);
        grid.setVgap(8);
        grid.setHgap(8);

        HBox hBox = new HBox(submitButton);
        hBox.setPrefHeight(grid.getPrefHeight());
        hBox.setAlignment(Pos.CENTER);
        hBox.setPadding(new Insets(12));

        HBox top = new HBox(grid, hBox);
        top.setPadding(new Insets(12));
        top.setAlignment(Pos.CENTER);

        StackPane bottom = new StackPane(result);
        bottom.setPadding(new Insets(4));

        BorderPane pane = new BorderPane();
        pane.setTop(top);
        pane.setBottom(bottom);

        return pane;
    }

    void listenEvents() {
        submitButton.setOnMouseClicked(event -> {
            if (toServer == null) return;

            double rate, year, amount, monthlyPayment, totalPayment;

            try {
                rate = Double.parseDouble(tfAnnualInterestRate.getText().trim());
                year = Double.parseDouble(tfNumberOfYears.getText().trim());
                amount = Double.parseDouble(tfLoanAmount.getText().trim());

                // Send to server
                toServer.writeDouble(rate);
                toServer.writeDouble(year);
                toServer.writeDouble(amount);
                toServer.flush();

                // Received to server
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

    void connectToServer() throws IOException {
        Socket socket = new Socket("127.0.0.1", 8080);
        fromServer = new DataInputStream(socket.getInputStream());
        toServer = new DataOutputStream(socket.getOutputStream());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
