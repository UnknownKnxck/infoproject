import atmmachine.*;

import java.util.Scanner;

public class Main {

    public static int menu() {
        Scanner in = new Scanner(System.in);
        int cho;

        System.out.println("Select an option: ");
        System.out.println("\t[0] Logout\n\t[1] Deposit\n\t[2] Withdraw\n\t[3] Check balance\n\t");
        cho = in.nextInt();

        return cho;
    }

    public static boolean login(String pin, String card) { //checks if username && password are correct or exist

        if (Security.encrypt(pin).equals(Csvreader.readpin(card))) {
            Logging.writeLog("Login", 0, card);
            return true;
        } else {
            System.out.println("Username and/or password couldn't be matched.");
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

        }
    }

}