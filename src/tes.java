import java.util.Scanner;
import java.util.Arrays;
import java.util.stream.Collectors;

public class tes {
    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            String name = "henry bitcoin";
            toTitleCase(name);
            checkNameFormat(name);

        } catch (Error e) {
            System.err.println(e.getMessage());
        }
    }

    public static String name() {
        System.out.print("Name: ");
        return scan.nextLine();
    }

    public static String toTitleCase(String input) {
        return Arrays.stream(input.trim().split("\\s+"))
                .map(word -> word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase())
                .collect(Collectors.joining(" "));
    }

    public static void checkNameFormat(String name) {
        // Check if the Name contains non alphabets or spaces, then throw an Error
        if (!name.matches("^[a-zA-Z ]+$")) {
            throw new Error("Name format is invalid! | Name must only contain alphabets\n");
        }
    }
}
