package atmmachine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Csvreader {
    static String inputUsername;

    private Csvreader(String username) {
        this.inputUsername = username;
    }

    static String fileName = "./src/atmmachine/users.csv"; //static log file path

    public static String readpin(String cardnr) {
        // Read the CSV file
        String line = "";
        String csvSplitBy = ";"; //symbol that separates the values in the csv file

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) { //try to read the file (if it exists)
            // Search for the username in the CSV file
            while ((line = br.readLine()) != null) {
                String[] userData = line.split(csvSplitBy); //split the line into an array
                String username = userData[0];
                String pin = userData[1];

                if (username.equals(cardnr)) {
                    // Found a matching username, return pin                   
                    return pin;
                }
            }
        } catch (IOException e) { //if file can't be read
            e.printStackTrace();
        }
        return null;
    }
}