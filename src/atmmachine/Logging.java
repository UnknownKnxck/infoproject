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


    public static void writeLog(int type, double amount, String cardnumber, String user) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis()); //get current time and date (YYYY-MM-DD HH:MM:SS.MS)
        String log = "";

        //Used to write to the log file
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("./src/atmmachine/logging.log", true))) { //if file doesn't exist, create it
            switch (type) {
                case 1 -> {
                    log = "[" + cardnumber + "]: " + "Deposit -> " + amount + "$ ";
                    ATM.addMoney(amount);
                }
                case 2 -> {
                    log = "[" + cardnumber + "]: " + "Withdrawal -> " + amount + "$ ";
                    ATM.subtractMoney(amount);
                }
                case 3 -> log = "[" + cardnumber + "]: " + "Login ";
                case 4 -> log = "[" + cardnumber + "]: " + "Logout ";
                case 5 -> log = "[" + cardnumber + "]: " + "Balance check ";
                case 6 -> log = "[" + cardnumber + "]: " + "filled up ATM ";
                case 7 -> log = "[" + cardnumber + "]: " + "Invalid option ";
                case 8 -> log = "[" + cardnumber + "]: " + "Added user [" + user + "] ";
                case 9 -> log = "[" + cardnumber + "]: " + "Delete user [" + user + "]";
            }
            bw.write(log + "(" + timestamp + ")"); //write timestamp to file
            bw.newLine();
        } catch (IOException e) { //if file can't be created
            e.printStackTrace();
        }
    }
}