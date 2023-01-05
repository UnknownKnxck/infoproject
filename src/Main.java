import atmmachine.*;

import java.util.Scanner;

public class Main {

    public static void menu(String cardnumber) { //prints the menu for the user to choose from, and returns the choice
        Scanner in = new Scanner(System.in);
        int cho;
        double amount;

        System.out.println("Select an option: ");
        System.out.println("\t[0] Logout\n\t[1] Deposit\n\t[2] Withdraw\n\t[3] Check balance\n\t");
        cho = in.nextInt();
        while (1 == 1) {
            switch (cho) {
                case 0 -> {
                    System.out.println("Logging out...");
                    Logging.writeLog(4, 0, cardnumber);
                    return;
                }
                case 1 -> {
                    System.out.println("How much would you like to deposit? ");
                    amount = in.nextDouble();
                    Logging.writeLog(1, amount, cardnumber);
                }
                case 2 -> {
                    System.out.println("How much would you like to withdraw? ");
                    amount = in.nextDouble();
                    Logging.writeLog(1, amount, cardnumber);
                }
                case 3 -> System.out.println("Check balance");
                default -> {
                    System.out.println("Invalid choice");
                    Logging.writeLog(7, 0, cardnumber);
                }
            }
            menu(cardnumber);
        }
    }

    public static boolean login(String pin, String card) { //checks if username && password are correct or exist
        if (Security.encrypt(pin).equals(Csvreader.readpin(card))) {
            Logging.writeLog(3, 0, card);
            return true;
        } else {
            System.out.println("Username and/or password couldn't be matched."); //if username and/or password are wrong
            return false;
        }
    }

    public static void main(String[] args) {
        String cardnumber, decryptedPin;

        ATM atm = new ATM("Sterzing");

        Scanner in = new Scanner(System.in);
        System.out.println("Welcome to the ATM of " + atm.name);
        System.out.println("Please enter your cardnumber: ");
        cardnumber = in.nextLine();
        System.out.println("Please enter your PIN: ");
        decryptedPin = in.nextLine();
        //todo: If login is admin --> add admin actions
        if (login(decryptedPin, cardnumber)) {
            menu(cardnumber);
        }
    }
}