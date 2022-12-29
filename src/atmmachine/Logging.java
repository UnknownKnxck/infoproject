package atmmachine;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;

public class Logging {
    private double balance;

    private Logging() {
        this.balance = 0;
    }


    public static void writeLog(int type, double amount, String cardnumber) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis()); //get current time and date (YYYY-MM-DD HH:MM:SS.MS)
        String log = "";

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("./src/atmmachine/logging.log", true))) { //if file doesn't exist, create it
            switch (type) {
                case 1 -> log = "[" + cardnumber + "]: " + "Deposit -> " + amount + "$ ";
                case 2 -> log = "[" + cardnumber + "]: " + "Withdrawal -> " + amount + "$ ";
                case 3 -> log = "[" + cardnumber + "]: " + "Login ";
                case 4 -> log = "[" + cardnumber + "]: " + "Logout ";
                case 5 -> log = "[" + cardnumber + "]: " + "Balance check ";
            }
            bw.write(log + "(" + timestamp + ")");
            bw.newLine();
        } catch (IOException e) { //if file can't be created
            e.printStackTrace();
        }
    }
}