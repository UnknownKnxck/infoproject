package atmmachine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

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

    public static ArrayList createCustomers() throws IOException {
        String csvFile = "src/atmmachine/users.csv";
        String line = "";
        String csvSplitBy = ";";
        ArrayList<Customer> customers = new ArrayList<>();


        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                String[] customer = line.split(csvSplitBy);
                customers.add(new Customer(customer[0], customer[1], Double.valueOf(customer[2]), Boolean.valueOf(customer[3])));
            }
        }
        System.out.println(customers);
        return customers;
    }


    //TODO: WRITE NEW BALANCE IN CSV FILE
    public void withdraw(double amount, Customer c) {
        // Withdraw the amount from the balance
        if (amount > c.balance || amount > ATM.currentMoney) {
            System.out.println("ATM.currentMoney: " + ATM.currentMoney + "\n" + "Current Money User: " + balance);
            System.out.println("Withdraw amount exceeds allowed amount.");
            Logging.writeLog(10, amount, cardnumber, "");
        } else {
            balance = balance - amount;
        }
    }

    public void deposit(Customer c, double amount) {
        // Deposit the amount to the balance
        balance += amount;
    }

    public void balance(Customer c) {
        //get current balance of user
        System.out.println("Your current balance is: " + c.balance);
    }
}
