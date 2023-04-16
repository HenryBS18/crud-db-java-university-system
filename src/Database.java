import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {
    // Declare Objects
    private final Input input = new Input();
    private final InputValidation inputValidation = new InputValidation();

    // Declare Variables
    private String studentId;
    private String name;
    private String email;
    private String major;
    private int semester;
    private float ip;
    private String subject;
    private String updateScore;
    private float score;

    boolean isSubjectValid = true;

    // Declare Database Connection
    private Connection conn = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;
    private final String url = "jdbc:mysql://localhost/student";
    private final String username = "root";
    private final String password = "";

    public void connect() {
        // Create Database connection
        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new Error("Connection failed, Please check your localhost!");
        }
    }

    public void disconnect() {
        // Disconnect Database connection
        try {
            conn.close();

            System.out.println();
            System.out.println("===========================================");
            System.out.println("----- Thank you for using our System! -----");
            System.out.println("===========================================");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void addNewStudent() {
        System.out.println("=====================");
        System.out.println("-- Add New Student --");
        System.out.println("=====================");

        try {
            // Input Student Information, and check the format validation
            studentId = input.studentId();
            inputValidation.checkStudentIdFormat(studentId);

            name = input.toTitleCase(input.name());
            inputValidation.checkNameFormat(name);

            email = input.email();
            inputValidation.checkEmailFormat(email);

            major = input.toTitleCase(input.major());
            inputValidation.checkMajorFormat(major);

            semester = input.semester();
            input.newLine();
            inputValidation.checkSemesterFormat(semester);

            ip = input.ip();
            input.newLine();
            inputValidation.checkIpFormat(ip);

            // SQL Query to get Student ID from student_data table
            String query = "SELECT student_id FROM student_data WHERE student_id = ?;";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, studentId);
            rs = pstmt.executeQuery();

            // Check if the Student ID is already added, otherwise throw an Error
            while (rs.next()) {
                if (rs.getString("student_id").equals(studentId)) {
                    throw new Error("Student ID " + studentId + "has already been added to the system!!\n");
                }
            }

            // SQL Query to add new Student to student_data table
            query = "INSERT INTO student_data (student_id, name, email, major, semester, ip) " +
                    "VALUES (?, ?, ?, ?, ?, ?);";
            pstmt = conn.prepareStatement(query);

            pstmt.setString(1, studentId);
            pstmt.setString(2, name);
            pstmt.setString(3, email);
            pstmt.setString(4, major);
            pstmt.setInt(5, semester);
            pstmt.setFloat(6, ip);

            pstmt.executeUpdate();

            System.out.println(name + " added to the system!");

            // After add new Student to student_data table, then Create Student Score tables
            createStudentScore(studentId);
            System.out.println();

        } catch (SQLException e) {
            System.err.println("Connection failed");
            e.printStackTrace();
        } catch (Error e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void showAllStudent() {
        try {
            // SQL Query to get all Students information from student_data table
            String query = "SELECT * FROM student_data";
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();

            System.out.println("No | Student ID | Name | Email | Major | Semester | IP");
            System.out.println("------------------------------------------------------");

            // Declare local variables
            int i = 1;
            boolean isFound = false;

            // Print All Students information, then isFound = true, if the Student data is
            // found
            while (rs.next()) {
                System.out.println(i + " | " + rs.getString("student_id") + " | " + rs.getString("name") + " | "
                        + rs.getString("email") + " | " + rs.getString("major") + " | " + rs.getString("semester")
                        + " | " + rs.getString("ip"));
                i++;
                isFound = true;
            }
            System.out.println();

            // If Student not found, then throw an Error
            if (!isFound) {
                throw new Error("No Student Found\n");
            }
        } catch (SQLException e) {
            System.err.println("Connection failed");
            e.printStackTrace();
        } catch (Error e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void showStudentDataById(String studentId) {
        try {
            // SQL Query to get all Students information from student_data table
            String query = "SELECT * FROM student_data WHERE student_id = ?;";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, studentId);
            rs = pstmt.executeQuery();

            System.out.println("Student ID | Name | Email | Major | Semester | IP");
            System.out.println("-------------------------------------------------");

            // Print Student information
            while (rs.next()) {
                System.out.println(rs.getString("student_id") + " | " + rs.getString("name") + " | "
                        + rs.getString("email") + " | " + rs.getString("major") + " | " + rs.getString("semester")
                        + " | " + rs.getString("ip"));
            }
            System.out.println();

        } catch (SQLException e) {
            System.out.println("Connection failed");
            e.printStackTrace();
        } catch (Error e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateStudentDataById() {
        try {
            // Input Student ID, and check the format validation
            studentId = input.studentId();
            inputValidation.checkStudentIdFormat(studentId);

            // Check if the student exists
            checkStudentExistence();

            // Show the student information if exist
            showStudentDataById(studentId);

            // Input Student information, and check the format validation
            name = input.toTitleCase(input.name());
            inputValidation.checkNameFormat(name);

            email = input.email();
            inputValidation.checkEmailFormat(email);

            major = input.toTitleCase(input.major());
            inputValidation.checkMajorFormat(major);

            semester = input.semester();
            input.newLine();
            inputValidation.checkSemesterFormat(semester);

            ip = input.ip();
            input.newLine();
            inputValidation.checkIpFormat(ip);

            // SQL Query to update the Student information in the student_data table
            String query = "UPDATE student_data SET name = ?, email = ?, major = ?, semester = ?, ip = ? " +
                    "WHERE student_id = ?;";
            pstmt = conn.prepareStatement(query);

            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setString(3, major);
            pstmt.setInt(4, semester);
            pstmt.setFloat(5, ip);
            pstmt.setString(6, studentId);

            pstmt.executeUpdate();

            System.out.println(name + "'s data updated!\n");

        } catch (SQLException e) {
            System.out.println("Connection failed");
            e.printStackTrace();
        } catch (Error e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteStudentById() {
        try {
            // Input Student ID, and check the format validation
            studentId = input.studentId();
            inputValidation.checkStudentIdFormat(studentId);

            // Check if the student is exists
            checkStudentExistence();

            // Delete the Student Score tables first, before delete the Student from
            // student_data table
            deleteStudentScore(studentId);

            // SQL Query to delete the Student from student_data table
            String query = "DELETE FROM student_data WHERE student_id = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, studentId);
            pstmt.executeUpdate();

            System.out.println("Student ID " + studentId + " deleted from the system!\n");

        } catch (SQLException e) {
            System.out.println("Connection failed");
            e.printStackTrace();
        } catch (Error e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void showStudentScoreById() {
        try {
            // Input Student ID, and check the format validation
            studentId = input.studentId();
            inputValidation.checkStudentIdFormat(studentId);

            // Check if the student is exists
            checkStudentExistence();

            // Input Subject name
            subject = input.subject();

            // Reset the isSubjectValid value to true
            isSubjectValid = true;

            switch (subject) {
                case "1" -> subject = "score_calculus";
                case "2" -> subject = "score_web";
                case "3" -> subject = "score_oop";
                case "4" -> subject = "score_database";
                default -> {
                    isSubjectValid = false;
                    throw new Error("Subject is invalid!\n");
                }
            }

            // SQL Query to get Student name from student_data table
            String query = "SELECT name FROM student_data WHERE student_id = ?;";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, studentId);
            rs = pstmt.executeQuery();

            // Get The student name
            while (rs.next()) {
                name = rs.getString("name");
            }

            // SQL Query to get the Student Subject Scores from score table
            query = "SELECT * FROM " + subject + " WHERE student_id = ?;";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, studentId);
            rs = pstmt.executeQuery();

            switch (subject) {
                case "score_calculus" -> subject = "Calculus Scores: " + name;
                case "score_web" -> subject = "Web Scores: " + name;
                case "score_oop" -> subject = "OOP Scores: " + name;
                case "score_database" -> subject = "Database Scores: " + name;
            }

            System.out.println(subject);
            System.out.println("---------------------------------------------------");
            System.out.println("AFL 1 | AFL 2 | AFL 3 | AFL 4 | ALP | Average Score");
            System.out.println("---------------------------------------------------");

            // Get the Student Scores, then calculate the average, and print the results
            while (rs.next()) {
                float afl1 = rs.getFloat("afl_1");
                float afl2 = rs.getFloat("afl_2");
                float afl3 = rs.getFloat("afl_3");
                float afl4 = rs.getFloat("afl_4");
                float alp = rs.getFloat("alp");
                float averageScore = (afl1 + afl2 + afl3 + afl4 + alp) / 5;

                System.out.println(afl1 + " | " + afl2 + " | " + afl3 + " | " + afl4 + " | " + alp + " | "
                        + averageScore);
            }
            System.out.println();

            // Extract Substring to take the Subject name
            int index = subject.indexOf(" ");
            subject = "score_" + subject.substring(0, index).toLowerCase();

        } catch (SQLException e) {
            System.out.println("Connection failed");
            e.printStackTrace();
        } catch (Error e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void inputStudentScoreById() {
        try {
            // Show the Student Scores
            showStudentScoreById();

            // Check if Subject input in showStudentScoreById() is valid, if not, then
            // return
            if (!isSubjectValid) {
                return;
            }

            // Check if the Student ID format is valid
            inputValidation.checkStudentIdFormat(studentId);

            // Check if the student is exists
            checkStudentExistence();

            // Input Which Score that want to graded
            updateScore = input.updateScore();

            switch (updateScore) {
                case "1" -> updateScore = "afl_1";
                case "2" -> updateScore = "afl_2";
                case "3" -> updateScore = "afl_3";
                case "4" -> updateScore = "afl_4";
                case "5" -> updateScore = "alp";
                default -> throw new Error("Invalid Menu!\n");
            }

            // Input score
            try {
                score = input.score();
            } catch (Exception e) {
                throw new Error("Score is invalid! | Score must be number between 0.00 and 100.00\n");
            }
            input.newLine();
            inputValidation.checkScoreFormat(score);

            // SQL Query to update the Subject Scores
            String query = "UPDATE " + subject + " SET " + updateScore + " = ? WHERE student_id = ?;";
            pstmt = conn.prepareStatement(query);

            pstmt.setFloat(1, Float.valueOf(score));
            pstmt.setString(2, studentId);
            pstmt.executeUpdate();

            System.out.println(name + "'s score updated!\n");

        } catch (SQLException e) {
            System.out.println("Connection failed");
            e.printStackTrace();
        } catch (Error e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void createStudentScore(String studentId) {
        try {
            // SQL Query to create the Student Score tables
            String query = "INSERT INTO score_calculus (student_id, afl_1, afl_2, afl_3, afl_4, alp) " +
                    " VALUES (?, 0, 0, 0, 0, 0);";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, studentId);
            pstmt.executeUpdate();

            query = "INSERT INTO score_database (student_id, afl_1, afl_2, afl_3, afl_4, alp) " +
                    " VALUES (?, 0, 0, 0, 0, 0);";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, studentId);
            pstmt.executeUpdate();

            query = "INSERT INTO score_oop (student_id, afl_1, afl_2, afl_3, afl_4, alp) " +
                    " VALUES (?, 0, 0, 0, 0, 0);";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, studentId);
            pstmt.executeUpdate();

            query = "INSERT INTO score_web (student_id, afl_1, afl_2, afl_3, afl_4, alp) " +
                    " VALUES (?, 0, 0, 0, 0, 0);";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, studentId);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteStudentScore(String studentId) {
        try {
            // SQL Query to delete the student score tables
            String query = "DELETE FROM score_calculus WHERE student_id = ?;";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, studentId);
            pstmt.executeUpdate();

            query = "DELETE FROM score_database WHERE student_id = ?;";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, studentId);
            pstmt.executeUpdate();

            query = "DELETE FROM score_oop WHERE student_id = ?;";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, studentId);
            pstmt.executeUpdate();

            query = "DELETE FROM score_web WHERE student_id = ?;";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, studentId);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void checkStudentExistence() {
        try {
            // SQL Query to get Student ID from student_data table
            String query = "SELECT student_id FROM student_data WHERE student_id = ?;";
            pstmt = conn.prepareStatement(query);

            pstmt.setString(1, studentId);

            rs = pstmt.executeQuery();

            // Declare local variable
            boolean isStudentFound = false;

            // Check if Student ID is exists, if yes, then isStudentFound = true
            while (rs.next()) {
                if (rs.getString("student_id").equals(studentId)) {
                    isStudentFound = true;
                }
            }

            // If Student not found, then throw an Error
            if (!isStudentFound) {
                throw new Error("Student ID '" + studentId + "' Not Found! \n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
