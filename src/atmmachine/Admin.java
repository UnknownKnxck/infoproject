package atmmachine;

import java.util.ArrayList;

public class Admin {
    public String name;
    public String password;

    public Admin(String name, String password) {
        this.name = name;
        this.password = Security.encrypt(password);
    }

    public void addCustomer(String customer, String password) {

    }

    public void removeCustomer(Customer customer) {
        // Remove the customer from the database
    }
}

