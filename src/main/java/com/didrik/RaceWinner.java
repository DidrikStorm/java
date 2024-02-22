import java.io.File;
import java.io.FileNotFoundException;
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
                    readTextFile();
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

    public static void readTextFile() {
        // Skapa en Scanner för att läsa inmatning från användaren
        Scanner inputScanner = new Scanner(System.in);

        System.out.print("Enter the name of the file (including file extension): ");
        String fileName = inputScanner.next(); // Läs inmatningen från användaren
        fileName += ".txt"; // Lägg till ".txt" till filnamnet automatiskt

        try {
            // Skapa en Scanner för att läsa från den angivna textfilen
            Scanner fileScanner = new Scanner(new File(fileName));

            // Läs rad för rad från textfilen och skriv ut varje rad
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                System.out.println(line);
            }

            // Stäng Scanner efter att läsningen är klar
            fileScanner.close();

            System.out.println("\nReturning to Main Menu...\n");
        } catch (FileNotFoundException e) {
            // Hantera om filen inte hittades
            System.err.println("File not found: " + fileName);
            e.printStackTrace();
        } finally {
            // Stäng Scanner för användarinmatning
            inputScanner.nextLine(); // Konsumera återstående ny rad
        }
    }
}