import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class RaceWinner {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            displayMainMenu(); // Visa huvudmenyn

            // Läs användarens val
            choice = scanner.nextInt();

            // Hantera användarens val
            switch (choice) {
                case 1:
                    readAndSortTextFile();
                    break;
                case 2:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 2.");
            }
        } while (choice != 2);

        scanner.close();
    }

    public static void displayMainMenu() {
        System.out.println("Main Menu:");
        System.out.println("1. Read Text File");
        System.out.println("2. Quit");
        System.out.print("Enter your choice: ");
    }

    public static void readAndSortTextFile() {
        Scanner inputScanner = new Scanner(System.in);

        System.out.print("Enter the name of the file (including file extension): ");
        String fileName = inputScanner.next();
        fileName += ".txt";

        Map<String, Integer> totalTimeMap = new HashMap<>();

        try {
            Scanner fileScanner = new Scanner(new File(fileName));

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(",");

                if (parts.length >= 4) {
                    String name = parts[0].trim();
                    String startTime = parts[2].trim();
                    String endTime = parts[3].trim();

                    int resultTime = calculateResultTime(startTime, endTime);

                    // Uppdatera totaltid för den här deltagaren
                    totalTimeMap.put(name, totalTimeMap.getOrDefault(name, 0) + resultTime);
                } else {
                    System.err.println("Invalid row format:" + line);
                }
            }

            fileScanner.close();

            // Skriv ut totaltid för varje deltagare
            for (Map.Entry<String, Integer> entry : totalTimeMap.entrySet()) {
                System.out.println("Totaltime for " + entry.getKey() + ": " + entry.getValue() + " seconds");
            }

            System.out.println("\nReturning to Main Menu...\n");
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + fileName);
            e.printStackTrace();
        } finally {
            inputScanner.nextLine(); // Konsumera det återstående nytecknet
        }
    }

    public static int calculateResultTime(String startTime, String endTime) {
        try {
            // Analysera starttid och sluttid för att beräkna resultatet
            String[] startParts = startTime.split(":");
            String[] endParts = endTime.split(":");

            // Extrahera timmar, minuter och sekunder
            int startHours = Integer.parseInt(startParts[0]);
            int startMinutes = Integer.parseInt(startParts[1]);
            int startSeconds = Integer.parseInt(startParts[2]);

            int endHours = Integer.parseInt(endParts[0]);
            int endMinutes = Integer.parseInt(endParts[1]);
            int endSeconds = Integer.parseInt(endParts[2]);

            // Beräkna totala sekunder för starttid och sluttid
            int startTotalSeconds = startHours * 3600 + startMinutes * 60 + startSeconds;
            int endTotalSeconds = endHours * 3600 + endMinutes * 60 + endSeconds;

            // Beräkna resultatet i sekunder
            int resultTimeInSeconds = endTotalSeconds - startTotalSeconds;

            return resultTimeInSeconds;
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            System.err.println("Errors in interpreting time:" + e.getMessage());
            return 0; // Returnera 0 om det uppstår ett fel vid tolkning av tiden
        }
    }
}