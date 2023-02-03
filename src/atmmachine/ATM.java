package atmmachine;

import java.util.Random;

public class ATM {
    public static String name;
    public static double currentMoney;

    public ATM(String name, double money) {
        this.name = name;
        this.currentMoney = money;
    }

    public static String blowUp() {
        //MAKE THE POSSIBILITY 1/100 OF A BOMB TO EXPLODE

        Random r = new Random();
        int random = r.nextInt(100);
        if (random == 1) {
            return "ATM " + name + " was blown up successfully!";
        } else {
            return "You tried to rob the ATM " + name + ". Unfortunately you got caught by us.\nWe called 112.\nThe police is on its way.";
        }
    }

    public static void subtractMoney(double amount) {
        currentMoney -= amount;
    }

    public static void addMoney(double amount) {
        currentMoney += amount;
    }
}
