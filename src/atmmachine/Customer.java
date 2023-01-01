package atmmachine;

public class Customer {
    public String cardnumber, pin;
    public double balance;

    public Customer(String cardnumber, String pin, double balance) {
        this.cardnumber = cardnumber;
        this.pin = Security.encrypt(pin);
        this.balance = balance;
    }

    public void withdraw(double amount) {
        // Withdraw the amount from the balance
    }

    public void deposit(double amount) {
        // Deposit the amount to the balance
    }

}
