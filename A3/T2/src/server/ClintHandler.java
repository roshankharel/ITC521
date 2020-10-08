package server;

import javafx.application.Platform;
import javafx.scene.control.TextArea;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.function.Consumer;

public class ClintHandler implements Runnable {
    private final Socket socket;
    private final Consumer<String> writeTextArea;

    public ClintHandler(Socket socket, Consumer<String> writeTextArea) {
        this.socket = socket;
        this.writeTextArea = writeTextArea;
    }

    @Override
    public void run() {
        try {
            // Create data input and output streams
            DataInputStream inputFromClient = new DataInputStream(socket.getInputStream());
            DataOutputStream outputToClient = new DataOutputStream(socket.getOutputStream());

            do {
                double rate, year, amount, monthlyPayment, totalPayment;

                // Receive data from the client
                rate = inputFromClient.readDouble();
                year = inputFromClient.readDouble();
                amount = inputFromClient.readDouble();


                totalPayment = amount + (amount * year * rate / 100);
                monthlyPayment = totalPayment / (year * 12);

                outputToClient.writeDouble(monthlyPayment);
                outputToClient.writeDouble(totalPayment);
                outputToClient.flush();

                Platform.runLater(() -> writeTextArea.accept(String.format(
                        "Annual Interest Rate: %f\n" +
                                "Number of Years: %f\n" +
                                "Loan Amount: %f\n" +
                                "Monthly Payment: %f\n" +
                                "Total Payment: %f\n\n",
                        rate, year, amount, monthlyPayment, totalPayment
                )));
            } while (true);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
