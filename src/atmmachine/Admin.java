package atmmachine;

import java.util.ArrayList;

public class Admin {
    public String name;
    public String password;

    public Admin(String name, String password) {
        this.name = name;
        this.password = Security.encrypt(password);
    }

    public static void addCustomer(String card, String pin) {


    }

    public static void removeCustomer(String card) {
        // Remove the customer from the database
    }

}
