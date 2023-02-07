package atmmachine;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * This class is used to read the users.csv file and return the data in a 2D array && manage the user
 **/

public class Customer {
    public String cardnumber, pin;
    public double balance;
    public boolean admin;

    public Customer(String cardnumber, String pin, double balance, boolean isAdmin) {
        this.cardnumber = cardnumber;
        this.pin = pin;
        this.balance = balance;
        this.admin = isAdmin;
    }

    public static ArrayList createCustomers() {
        String csvFile = "src/atmmachine/users.csv";
        String line = "";
        String csvSplitBy = ";";
        ArrayList<Customer> customers = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                String[] customer = line.split(csvSplitBy);
                customers.add(new Customer(customer[0], customer[1], Double.valueOf(customer[2]), Boolean.valueOf(customer[3])));
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("IO Exception");
        }
        return customers;
    }


    public void withdraw(double amount, Customer c) {
        if (amount > c.balance || amount > ATM.currentMoney) {
            System.out.println("ATM.currentMoney: " + ATM.currentMoney + "\n" + "Current Money User: " + c.balance);
            System.out.println("Withdraw amount exceeds allowed amount.");
            Logging.writeLog(10, amount, c.cardnumber, "");
        } else {
            c.balance = c.balance - amount;
        }
    }

    public void deposit(Customer c, double amount) {
        c.balance += amount;
    }

    public void balance(Customer c) {
        System.out.println("Your current balance is: " + c.balance);
    }
}