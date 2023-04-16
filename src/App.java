public class App {
    // Declare Objects
    private static final Database db = new Database();
    private static final Input input = new Input();

    // Declare Variables
    private static String menu;
    private static boolean isExit = false;

    public static void main(String[] args) {
        try {
            // Create the database connection
            db.connect();

            // Main Program
            do {
                System.out.println("===========================================");
                System.out.println("---- Welcome to the University System -----");
                System.out.println("===========================================");

                System.out.println("1. Add New Student");
                System.out.println("2. View All Students Information");
                System.out.println("3. Update Student Information");
                System.out.println("4. Input/Update Student Score By Student ID");
                System.out.println("5. View Student Score By Student ID");
                System.out.println("6. Delete Student by Student ID");
                System.out.println("7. Exit System");

                // Input Menu
                menu = input.menu();

                // Menu
                switch (menu) {
                    case "1" -> db.addNewStudent();
                    case "2" -> db.showAllStudent();
                    case "3" -> db.updateStudentDataById();
                    case "4" -> db.inputStudentScoreById();
                    case "5" -> db.showStudentScoreById();
                    case "6" -> db.deleteStudentById();
                    case "7" -> {
                        db.disconnect();
                        isExit = true;
                    }
                    default -> System.out.println("Invalid Menu!\n");
                }
            } while (!isExit);
        } catch (Error e) {
            System.err.println(e.getMessage());
        }
    }
}
