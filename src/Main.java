import atmmachine.*;

import java.util.Scanner;

public class Main {


    public static void adminMenu(String name) {
        Scanner in = new Scanner(System.in);
        int cho = in.nextInt();
        while (true) {
            switch (cho) {
                case 0 -> {
                    System.out.println("Logging out...");
                    Logging.writeLog(4, 0, name, "");
                    return;
                }
                case 1 -> {
                    System.out.println("Enter the card number of the customer you want to add: ");
                    String cardnumber = in.nextLine();
                    System.out.println("Enter the pin of the customer you want to add: ");
                    String pin = in.nextLine();
                    Admin.addCustomer(cardnumber, pin);
                    Logging.writeLog(8, 0, cardnumber, name);
                }
                case 2 -> {
                    System.out.println("Enter the card number of the customer you want to delete: ");
                    String cardnumber = in.nextLine();
                    Admin.removeCustomer(cardnumber);
                    Logging.writeLog(9, 0, cardnumber, name);
                }
                case 3 -> {
                    System.out.println("Enter the amount of money you want to add to the ATM: ");
                    double amount = in.nextDouble();
                    ATM.addMoney(amount);
                    Logging.writeLog(6, amount, name, "");
                }
                default -> {
                    System.out.println("Invalid choice, please try again.");
                    Logging.writeLog(7, 0, name, "");
                    cho = in.nextInt();
                }

            }

        }

    }

    public static void regularMenu(String cardnumber) { //prints the menu for the user to choose from, and returns the choice
        Scanner in = new Scanner(System.in);
        int cho = 0;
        double amount;

        System.out.println("Select an option: ");
        System.out.println("\t[0] Logout\n\t[1] Deposit\n\t[2] Withdraw\n\t[3] Check balance\n\t");
        cho = in.nextInt();
        while (true) {
            switch (cho) {
                case 0 -> {
                    System.out.println("Logging out...");
                    Logging.writeLog(4, 0, cardnumber, "");
                    main(null);
                }
                case 1 -> {
                    System.out.println("How much would you like to deposit? ");
                    amount = in.nextDouble();
                    Customer.deposit(amount);
                    Logging.writeLog(1, amount, cardnumber, "");
                }
                case 2 -> {
                    System.out.println("How much would you like to withdraw? ");
                    amount = in.nextDouble();
                    Customer.withdraw(amount, cardnumber);
                    Logging.writeLog(1, amount, cardnumber, "");
                }
                case 3 -> {
                    Customer.balance(cardnumber);
                    Logging.writeLog(5, 0, cardnumber, "");
                }
                case 69 -> {
                    System.out.println(ATM.blowUp());
                }
                default -> {
                    System.out.println("Invalid choice");
                    Logging.writeLog(7, 0, cardnumber, "");
                }
            }
            regularMenu(cardnumber);
        }
    }

    public static boolean login(String pin, String card) { //checks if username && password are correct or exist
        if (Security.encrypt(pin).equals(Csvreader.readpin(card))) {
            Logging.writeLog(3, 0, card, "");
            return true;
        } else {
            System.out.println("Username and/or password couldn't be matched."); //if username and/or password are wrong
            return false;
        }
    }

    public static void main(String[] args) {
        String cardnumber = null, decryptedPin = null;

        ATM atm = new ATM("Sterzing", 1500);


        Scanner in = new Scanner(System.in);
        System.out.println("Welcome to the ATM of " + atm.name);
        System.out.println("Please enter your cardnumber: ");
        cardnumber = in.nextLine();
        System.out.println("Please enter your PIN: ");
        decryptedPin = in.nextLine();
        //If login is admin --> add admin actions
        if (login(decryptedPin, cardnumber) && !Csvreader.isAdmin(cardnumber)) {
            regularMenu(cardnumber);
        } else if (login(decryptedPin, cardnumber) && Csvreader.isAdmin(cardnumber)) {
            adminMenu(cardnumber);
        }
    }
}
