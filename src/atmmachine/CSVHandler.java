package atmmachine;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVHandler {
    static File users = new File("src/atmmachine/users.csv");

    public static void deleteLine(String cardNumber) throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(users))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.contains(cardNumber)) {
                    lines.add(line);
                }
            }
        }
        try (FileWriter writer = new FileWriter(users)) {
            for (String line : lines) {
                writer.append(line).append("\n");
            }
        }
    }


    public static void addCustomerToCSV(Customer c) throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(users))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            lines.add(c.cardnumber + ";" + c.pin + ";" + c.balance + ";" + c.admin + "\n");
        }
        try (FileWriter writer = new FileWriter(users)) {
            for (String line : lines) {
                writer.append(line).append("\n");
            }
        }
        //FIX: REMOVING ADDS A NEW LINE => CANNOT EXECUTE WITHOUT DELETING IT
    }

    public static void changeBalance() {

    }

}