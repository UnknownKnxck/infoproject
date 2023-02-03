package atmmachine;

public class Customer {
    public String cardnumber, pin;
    public static double balance;

    private Customer(String cardnumber, String pin) {
        this.cardnumber = cardnumber;
        this.pin = Security.encrypt(pin);
        this.balance = Csvreader.getBalance(cardnumber);
    }

    public static void withdraw(double amount) {
        // Withdraw the amount from the balance
        if (amount > balance) {
            System.out.println("Withdraw amount exceeded account balance");
            return;
        }
        balance = balance - amount;
    }

    public static void deposit(double amount) {
        // Deposit the amount to the balance
        balance += amount;
    }

    public static void balance(String cardnr) {
        //get current balance of user
        balance = Csvreader.getBalance(cardnr);
        System.out.println("Your current balance is: " + balance);
    }
}
