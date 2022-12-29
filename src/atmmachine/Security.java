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
            /* MessageDigest instance for MD5. */
            MessageDigest m = MessageDigest.getInstance("MD5");

            /* Add plain-text password bytes to digest using MD5 update() method. */
            m.update(pin.getBytes());

            /* Convert the hash value into bytes */
            byte[] bytes = m.digest();

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

    public static void main(String[] args) {
        String inp;
        Scanner in = new Scanner(System.in);
        inp = in.nextLine();
        System.out.println(encrypt(inp));

    }
}



