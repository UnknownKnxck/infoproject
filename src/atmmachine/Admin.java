package atmmachine;

import java.util.Scanner;

public class Admin {
    public String name;
    public String password;

    public Admin(String name, String password) {
        this.name = name;
        this.password = Security.encrypt(password);
    }

    public static String[] addCustomer() {
        //Input of cardnumber and pin
        Scanner input = new Scanner(System.in);

        System.out.print("Enter cardnumber you would like to add: ");
        String cardnumber = input.nextLine();

        System.out.print("Enter pin of the user: ");
        String pin = Security.encrypt(input.nextLine());

        return new String[]{cardnumber, pin};

    }


    public static String[] deleteCustomer() {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter cardnumber you would like to remove: ");
        String cardnumber = input.nextLine();


        return new String[]{cardnumber, null};
    }
}
