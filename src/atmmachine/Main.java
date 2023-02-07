package atmmachine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static ArrayList<Customer> customers = Customer.createCustomers(); //Creates an ArrayList of customers from the CSV file
    static String welcome = "Welcome to the atm of ";


    /*Method to print Line before and after welcome message*/
    public static void printLine(String atmName) {
        int lineLength = welcome.length() + atmName.length();
        String line = "-".repeat(lineLength);
        System.out.println(line);
    }

    /*Method to manage the ArrayList, in case a user gets deleted or added*/
    public static void arrayListManager(int option, String[] user) {
        customers = Customer.createCustomers();
        switch (option) {
            case 1 -> {
                customers.add(new Customer(user[0], Security.encrypt(user[1]), Double.valueOf(user[2]), Boolean.valueOf(user[3]))); //Add new user to ArrayList
                CSVHandler.addCustomerToCSV(customers.get(customers.size() - 1)); //Adds the new customer to the CSV file
            }
            case 2 -> {
                for (Customer c : customers) {
                    if (user[0].equals(c.cardnumber)) {
                        CSVHandler.deleteLine(c.cardnumber); //Deletes the customer from the CSV file
                        customers.remove(c); //Removes the customer from the ArrayList
                    }
                }
            }
        }
    }


    public static void main(String[] args) {
        ATM atm = new ATM("Sterzing", 1500);

        String cardnumber = null, decryptedPin = null;
        Scanner in = new Scanner(System.in);

        printLine(atm.name);
        System.out.println(welcome + atm.name);
        printLine(atm.name);


        System.out.println("Please enter your cardnumber: ");
        cardnumber = in.nextLine();
        System.out.println("Please enter your PIN: ");
        decryptedPin = in.nextLine();

        for (Customer c : customers) {
            if (c.cardnumber.equals(cardnumber) && c.pin.equals(Security.encrypt(decryptedPin))) {
                if (!c.admin) {
                    Menus.regularMenu(c);
                } else if (c.admin) {
                    Menus.adminMenu(c);
                }
                Logging.writeLog(3, 0, c.cardnumber, "");
                break;
            }
        }
    }
}