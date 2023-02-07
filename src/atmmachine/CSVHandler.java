package atmmachine;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVHandler {
    static File users = new File("src/atmmachine/users.csv");

    public static void deleteLine(String cardNumber) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(users))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.contains("" + cardNumber + ";")) {
                    lines.add(line);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("IO Exception");
        }
        try (FileWriter writer = new FileWriter(users)) {
            for (String line : lines) {
                if (line.equals(lines.get(lines.size() - 1))) {
                    writer.append(line);
                } else {
                    writer.append(line).append("\n");
                }
            }
        } catch (IOException e) {
            System.out.println("IO Exception");
        }
    }


    public static void addCustomerToCSV(Customer c) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(users))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            lines.add(c.cardnumber + ";" + c.pin + ";" + c.balance + ";" + c.admin + "\n");
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("IO Exception");
        }

        try (FileWriter writer = new FileWriter(users)) {
            for (String line : lines) {
                if (line.equals(lines.get(lines.size() - 1))) {
                    writer.append(line);
                } else {
                    writer.append(line).append("\n");
                }
            }
        } catch (IOException e) {
            System.out.println("IO Exception");
        }
        //FIX: REMOVING ADDS A NEW LINE => CANNOT EXECUTE WITHOUT DELETING IT
    }

    public static void changeBalance(Customer c) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(users))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.out.println("IO Exception");
        }

        for (int i = 0; i < lines.size(); i++) {
            String[] values = lines.get(i).split(";");
            if (values[0].equals(c.cardnumber)) {
                values[2] = String.valueOf(c.balance);
            }
            lines.set(i, String.join(";", values));
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(users))) {
            for (String line : lines) {
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("IO Exception");
        }
    }
}