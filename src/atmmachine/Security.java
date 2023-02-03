package atmmachine;

import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;
import java.util.Scanner;


/**
 * This class is used to encrypt passwords.
 */

public class Security {
    String pin = "";

    private Security(String pin) {
        this.pin = pin;
    }

    public static String encrypt(String pin) {
        String encryptedPin = "";
        try {
            //MessageDigest --> This class provides applications the functionality of a message digest algorithm, such as SHA-1 or SHA-256.
            MessageDigest m = MessageDigest.getInstance("MD5"); //MD5 hashing algorithm

            /* Add plain-text password bytes to digest using MD5 update() method. */
            m.update(pin.getBytes());

            byte[] bytes = m.digest();  //Convert the hash value into byte

            /* The bytes array has bytes in decimal form. Converting it into hexadecimal format. */
            StringBuilder s = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }

            /* Complete hashed password in hexadecimal format */
            encryptedPin = s.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return encryptedPin;
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the pin: ");
        String pin = sc.nextLine();
        System.out.println("Encrypted pin: " + Security.encrypt(pin));
    }
}





