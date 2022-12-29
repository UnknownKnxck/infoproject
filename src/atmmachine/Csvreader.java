package atmmachine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Csvreader {
    static String inputUsername;

    private Csvreader(String username) {
        this.inputUsername = username;
    }

    static String fileName = "./src/atmmachine/users.csv";

    public static String readpin(String cardnr) {
        // Read the CSV file
        String line = "";
        String csvSplitBy = ";";

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {


            // Search for the username in the CSV file
            while ((line = br.readLine()) != null) {
                String[] userData = line.split(csvSplitBy);
                String username = userData[0];
                String pin
                        = userData[1];

                if (username.equals(cardnr)) {
                    // Found a matching username, return pin                   
                    return pin;
                }
            }
            if (line == null) {
                // No matching username was found
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}