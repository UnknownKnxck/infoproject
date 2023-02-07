package atmmachine;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

/**
 * This class is used to encrypt the pin of the user using the MD5 Algorithm.
 **/
public class Security {
    private String pin;

    public Security(String pin) {
        this.pin = pin;
    }

    public static String encrypt(String pin) {
        try {
            // Create a MessageDigest instance with MD5 algorithm
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");

            // Add the pin to the digest
            messageDigest.update(pin.getBytes());

            // Convert the hash value into bytes
            byte[] bytes = messageDigest.digest();

            // Convert the bytes array into hexadecimal format
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                stringBuilder.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }

            // Return the complete hashed password in hexadecimal format
            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            // If the specified algorithm is not available in the environment
            System.out.println("The specified algorithm is not available in the environment");
            return "";
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter pin: ");
        String pin = input.nextLine();
        System.out.println("Encrypted pin: " + Security.encrypt(pin));
    }
}
