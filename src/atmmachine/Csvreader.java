package atmmachine;

import java.io.*;
import java.util.ArrayList;

/**
 * This class is used to get Data from a CSV File
 */

public class Csvreader {
    static String inputUsername;

    private Csvreader(String username) {
        this.inputUsername = username;
    }

    static File usersFile = new File("./src/atmmachine/users.csv"); //static log file path
    static File balanceFile = new File("./src/atmmachine/customer.csv"); //static log file path

    public static String readpin(String cardnr) {
        // Read the CSV file
        String line = "";
        String csvSplitBy = ";"; //symbol that separates the values in the csv file

        try (BufferedReader br = new BufferedReader(new FileReader(usersFile))) { //try to read the file (if it exists)
            // Search for the username in the CSV file
            while ((line = br.readLine()) != null) {
                String[] userData = line.split(csvSplitBy); //split the line into an array
                String username = userData[0];
                String pin = userData[1];

                if (username.equals(cardnr)) {
                    //Found a matching username, return pin
                    return pin;
                }
            }
        } catch (IOException e) { //if file can't be read
            e.printStackTrace();
        }
        return null;
    }

    public static double getBalance(String cardnumber) {
        String line = "";
        String csvSplitBy = ";";
        double balance = 0;
        String b1 = "123";

        try (BufferedReader br = new BufferedReader(new FileReader(balanceFile))) { //try to read the file (if it exists)
            // Search for the username in the CSV file
            while ((line = br.readLine()) != null) {
                String[] userData = line.split(csvSplitBy); //split the line into an array
                String card = userData[0];
                b1 = userData[1];

                if (card.equals(cardnumber)) {
                    balance = Double.parseDouble(b1);
                }
            }
        } catch (IOException e) { //if file can't be read
            e.printStackTrace();
        }
        return balance;
    }

    public void addCustomer(String customer, String password) {
        //Write to the CSV file


    }

    public void removeCustomer(Customer customer) {
        // Remove the customer from the database
    }

    public static boolean isAdmin(String cardnumber) {
        String line = "";
        String csvSplitBy = ";";
        boolean admin = false;

        try (BufferedReader br = new BufferedReader(new FileReader(usersFile))) { //try to read the file (if it exists)
            // Search for the username in the CSV file
            while ((line = br.readLine()) != null) {
                String[] userData = line.split(csvSplitBy); //split the line into an array
                String card = userData[0];
                String admin1 = userData[2];
                if (admin1.equals("true") && card.equals(cardnumber)) {
                    admin = true;
                }
            }
        } catch (IOException e) { //if file can't be read
            e.printStackTrace();
        }
        return admin;
    }


    //TODO: Create a function that reads all users in the "users.csv" file, and stores them in an arraylist

}