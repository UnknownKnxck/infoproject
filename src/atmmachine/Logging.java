package atmmachine;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Logging {
    private double balance;

    private Logging() {
        this.balance = 0;
    }


    public static void writeLog(String transactionType, double amount, String cardnumber) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("./src/atmmachine/logging.log", true))) {
            String transaction = String.format("%s: %s %.2f", cardnumber, transactionType, amount);
            bw.write(transaction);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}