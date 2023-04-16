import java.util.Scanner;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Input {
    // Declare Object
    private final Scanner scan = new Scanner(System.in);

    // Functions to take user input with scanner and return the value
    public String menu() {
        System.out.print("Menu: ");
        return scan.nextLine();
    }

    public String studentId() {
        System.out.print("Student ID: ");
        return scan.nextLine();
    }

    public String name() {
        System.out.print("Name: ");
        return scan.nextLine();
    }

    public String email() {
        System.out.print("Email: ");
        return scan.nextLine();
    }

    public String major() {
        System.out.print("Major: ");
        return scan.nextLine();
    }

    public int semester() {
        System.out.print("Semester: ");
        return scan.nextInt();
    }

    public float ip() {
        System.out.print("IP: ");
        return scan.nextFloat();
    }

    public String subject() {
        System.out.println("--------------");
        System.out.println("-- Subjects --");
        System.out.println("--------------");

        System.out.println("1. Calculus");
        System.out.println("2. Web");
        System.out.println("3. OOP");
        System.out.println("4. Database");

        System.out.print("Subject: ");
        return scan.nextLine();
    }

    public String updateScore() {
        System.out.println("------------------");
        System.out.println("-- Update Score --");
        System.out.println("------------------");
        System.out.println("1. AFL 1");
        System.out.println("2. AFL 2");
        System.out.println("3. AFL 3");
        System.out.println("4. AFL 4");
        System.out.println("5. ALP");

        System.out.print("Update Score: ");
        return scan.nextLine();
    }

    public float score() {
        System.out.print("Score: ");
        return scan.nextFloat();
    }

    // Function to fix nextLine() and nextFloat() bug
    public String newLine() {
        return scan.nextLine();
    }

    // Function to make convert a String to TitleCase format
    public String toTitleCase(String input) {
        return Arrays.stream(input.trim().split("\\s+"))
                .map(word -> word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase())
                .collect(Collectors.joining(" "));
    }
}
