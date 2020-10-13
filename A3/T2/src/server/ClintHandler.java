package server;

import javafx.application.Platform;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.function.Consumer;

/**
 * ClintHandler
 *
 * The ClintHandler class is responsible for handling communication with the client.
 *
 * @author Roshan Kharel
 */
public class ClintHandler implements Runnable {
    private final Socket socket;
    private final Consumer<String> writeTextArea;

    /**
     * constructor method
     *
     * @param socket a client socket instance
     * @param writeTextArea a Consumer function that writes to the textarea
     */
    public ClintHandler(Socket socket, Consumer<String> writeTextArea) {
        this.socket = socket;
        this.writeTextArea = writeTextArea;
    }

    /**
     * method is invoked by thread start method call
     */
    @Override
    public void run() {
        try {
            // Create data input and output streams
            DataInputStream inputFromClient = new DataInputStream(socket.getInputStream());
            DataOutputStream outputToClient = new DataOutputStream(socket.getOutputStream());

            do {
                // initialize vars
                double rate, year, amount, interestAmount,  monthlyPayment, totalPayment;

                // Receive data from the client
                rate = inputFromClient.readDouble();
                year = inputFromClient.readDouble();
                amount = inputFromClient.readDouble();

                // calculate loan
                interestAmount = (amount * year * rate / 100);
                totalPayment = amount + interestAmount;
                monthlyPayment = totalPayment / (year * 12);

                // send calculated data to the client
                outputToClient.writeDouble(monthlyPayment);
                outputToClient.writeDouble(totalPayment);
                outputToClient.flush();

                // write to the textarea of communicated data
                Platform.runLater(() -> writeTextArea.accept(String.format(
                        "Annual Interest Rate: %f\n" +
                                "Number of Years: %f\n" +
                                "Loan Amount: %f\n" +
                                "Monthly Payment: %f\n" +
                                "Total Payment: %f\n\n",
                        rate, year, amount, monthlyPayment, totalPayment
                )));
            } while (true); // run indefinitely

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
