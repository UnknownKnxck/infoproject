import atmmachine.*;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static ArrayList<Customer> customers;

    static {
        try {
            customers = Customer.createCustomers();
        } catch (IOException e) {
            System.out.println("asjhidskjahjksd");
            throw new RuntimeException(e);
        }
    }


    public static void arrayListManager(int option, String[] user) throws IOException {
        switch (option) {
            case 1 -> {
                customers.add(new Customer(user[0], Security.encrypt(user[1]), Double.valueOf(user[2]), Boolean.valueOf(user[3])));
                CSVHandler.addCustomerToCSV(customers.get(customers.size()-1));
            }
            case 2 -> {
                for (Customer c : customers) {
                    if (user[0].equals(c.cardnumber)) {
                        CSVHandler.deleteLine(c.cardnumber);
                        customers.remove(c);
                    } else {
                        //FIX: PRINTS THIS SOMETIMES AFTER REMOVING
                        System.out.println("Couldn't find user.");
                    }
                }
            }
        }
    }

    public static void adminMenu(Customer c) throws IOException {
        Scanner in = new Scanner(System.in);
        int cho = 0;
        boolean check = true;

        System.out.println("Select an option: ");
        while (check) {
            System.out.println("\t[0] Logout\n\t[1] Add Customer\n\t[2] Remove Customer\n\t[3] Fill up ATM\n\t");
            cho = in.nextInt();
            switch (cho) {
                case 0 -> {
                    System.out.println("Logging out...");
                    Logging.writeLog(4, 0, c.cardnumber, "");
                    check = false;
                }
                case 1 -> {
                    String user[] = Admin.addCustomer();
                    arrayListManager(1, user);
                    Logging.writeLog(8, 0, c.cardnumber, "");
                }
                case 2 -> {
                    String user[] = Admin.deleteCustomer();
                    arrayListManager(2, user);
                    Logging.writeLog(9, 0, c.cardnumber, "");
                }
                case 3 -> {
                    double amount = ATM.addMoney();
                    Logging.writeLog(6, amount, c.cardnumber, "");
                }
                default -> {
                    System.out.println("Invalid choice");
                    Logging.writeLog(7, 0, c.cardnumber, "");
                }
            }
        }
    }

    public static void regularMenu(Customer c) { //prints the menu for the user to choose from, and returns the choice
        Scanner in = new Scanner(System.in);
        int cho = 0;
        double amount;
        boolean check = true;

        System.out.println("Select an option: ");
        while (check) {
            System.out.println("\t[0] Logout\n\t[1] Deposit\n\t[2] Withdraw\n\t[3] Check balance\n\t");
            cho = in.nextInt();
            switch (cho) {
                case 0 -> {
                    System.out.println("Logging out...");
                    Logging.writeLog(4, 0, c.cardnumber, "");
                    check = false;
                }
                case 1 -> {
                    System.out.println("How much would you like to deposit? ");
                    amount = in.nextDouble();
                    c.deposit(c, amount);
                    Logging.writeLog(1, amount, c.cardnumber, "");
                }
                case 2 -> {
                    System.out.println("How much would you like to withdraw? ");
                    amount = in.nextDouble();
                    c.withdraw(amount, c);
                    Logging.writeLog(1, amount, c.cardnumber, "");
                }
                case 3 -> {
                    System.out.println(c.balance);
                    Logging.writeLog(5, 0, c.cardnumber, "");
                }
                case 69 -> {
                    System.out.println(ATM.blowUp());
                }
                default -> {
                    System.out.println("Invalid choice");
                    Logging.writeLog(7, 0, c.cardnumber, "");
                }
            }
        }
    }

    public static boolean login(String pin, Customer c) { //checks if username && password are correct or exist
        if (Security.encrypt(pin).equals(c.pin)) {
            Logging.writeLog(3, 0, c.cardnumber, "");
            return true;
        } else {
            System.out.println("Username and/or password couldn't be matched."); //if username and/or password are wrong
            return false;
        }
    }

    public static void main(String[] args) throws IOException {
        String cardnumber = null, decryptedPin = null;


        ATM atm = new ATM("Sterzing", 1500);

        Scanner in = new Scanner(System.in);
        System.out.println("Welcome to the ATM of " + atm.name);
        System.out.println("Please enter your cardnumber: ");
        cardnumber = in.nextLine();
        System.out.println("Please enter your PIN: ");
        decryptedPin = in.nextLine();

        for (Customer c : customers) {
            System.out.println(c.admin);
            if (c.cardnumber.equals(cardnumber) && c.pin.equals(Security.encrypt(decryptedPin)) && !c.admin) {
                regularMenu(c);
            } else if (c.cardnumber.equals(cardnumber) && c.pin.equals(Security.encrypt(decryptedPin)) && c.admin) {
                adminMenu(c);
            }
        }
    }
}

