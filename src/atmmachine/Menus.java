package atmmachine;

import java.io.IOException;
import java.util.Scanner;

public class Menus {
    public static void adminMenu(Customer c) {
        Scanner in = new Scanner(System.in);
        int cho = 0;
        boolean check = true;

        System.out.println("Select an option: ");
        while (check) {
            System.out.println("\t[0] Logout\n\t[1] Add Customer\n\t[2] Remove Customer\n\t[3] Fill up ATM\n\t");
            try {
                cho = in.nextInt();

                switch (cho) {
                    case 0 -> {
                        System.out.println("Logging out...");
                        Logging.writeLog(4, 0, c.cardnumber, "");
                        check = false;
                    }
                    case 1 -> {
                        String[] user = Admin.addCustomer();
                        Main.arrayListManager(1, user);
                        Logging.writeLog(8, 0, c.cardnumber, "");
                    }
                    case 2 -> {
                        String[] user = Admin.deleteCustomer();
                        Main.arrayListManager(2, user);
                        Logging.writeLog(9, 0, c.cardnumber, "");
                    }
                    case 3 -> {
                        double amount = ATM.addMoney();
                        Logging.writeLog(6, amount, c.cardnumber, "");
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice");
                Logging.writeLog(7, 0, c.cardnumber, "");
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
            try {
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
                        CSVHandler.changeBalance(c);
                        Logging.writeLog(1, amount, c.cardnumber, "");
                    }
                    case 2 -> {
                        System.out.println("How much would you like to withdraw? ");
                        amount = in.nextDouble();
                        c.withdraw(amount, c);
                        CSVHandler.changeBalance(c);
                        Logging.writeLog(1, amount, c.cardnumber, "");
                    }
                    case 3 -> {
                        System.out.println(c.balance);
                        Logging.writeLog(5, 0, c.cardnumber, "");
                    }
                    case 69 -> {
                        System.out.println(ATM.blowUp());
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice");
                Logging.writeLog(7, 0, c.cardnumber, "");
            }
        }
    }
}