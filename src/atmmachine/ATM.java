package atmmachine;

import java.util.Random;

public class ATM {
    public String name;

    public ATM(String name) {
        this.name = name;
    }

    public String sprengung() {
        //MAKE THE POSSIBILITY 1/100 OF A BOMB TO EXPLODE

        Random r = new Random();
        int random = r.nextInt(100);
        if (random == 1) {
            return "Automat " + name + " wurde erfolgreich gesprengt";
        } else {
            return "nothing";
        }
    }

    public void withdraw(String cardnumber) {


    }

    public void deposit() {
    }

    public boolean login() {

        return false;
    }
}
