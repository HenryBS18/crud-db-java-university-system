public class InputValidation {
    public void checkStudentIdFormat(String studentId) {
        // Declare local variable
        boolean isNumber = true;

        // Check if the Student ID contains non numeric characters, is not found, then
        // isNumber = false
        for (int i = 0; i < studentId.length(); i++) {
            if (!Character.isDigit(studentId.charAt(i))) {
                isNumber = false;
                break;
            }
        }

        // If the Student ID contains non numeric characters, then throw an Error
        if (!isNumber) {
            throw new Error("Student ID format is invalid! | Student ID must be number\n");
        }

        // Student ID must be 13 digits, if not, then throw an Error
        if (studentId.length() < 13 || studentId.length() > 13) {
            throw new Error("Student ID format is invalid! | Student ID must be 13 digits of numbers\n");
        }
    }

    public void checkNameFormat(String name) {
        // Check if the Name contains non alphabets or spaces, then throw an Error
        if (!name.matches("^[a-zA-Z ]+$")) {
            throw new Error("Name format is invalid! | Name must only contain alphabets\n");
        }
    }

    public void checkEmailFormat(String email) {
        // Check if the Email starts with @, then throw an Error
        if (email.indexOf("@") == 0) {
            throw new Error("Email format is invalid! | Email Format: name@student.ciputra.ac.id\n");
        }

        // Check if the Email starts with non character, then throw an Error
        else if (!Character.isLetter(email.charAt(0))) {
            throw new Error("Email format is invalid! | Email Format: name@student.ciputra.ac.id\n");
        }

        // Check if the Email does not contain '@student.ciputra.ac.id', then throw an
        // Error
        else if (!email.contains("@student.ciputra.ac.id")) {
            throw new Error("Email format is invalid! | Email Format: name@student.ciputra.ac.id\n");
        }
    }

    public void checkMajorFormat(String major) {
        if (!major.matches("^[a-zA-Z][a-zA-Z0-9]*$") && !Character.isDigit(major.charAt(0))) {
            throw new Error("Major format is invalid! | Major must be alphanumeric");
        }
    }

    public void checkSemesterFormat(int semester) {
        // Semester must be between 1 and 8, if not, then throw an Error
        if (semester < 1 || semester > 8) {
            throw new Error("Semester format is invalid! | Semester must be between 1 and 8\n");
        }
    }

    public void checkIpFormat(float ip) {
        // IP must be between 0.00 and 4.00, if not, then throw an Error
        if (ip < 0.00 || ip > 4.00) {
            throw new Error("IP format is invalid! | IP must be between 0 and 4.00\n");
        }
    }

    public void checkScoreFormat(float score) {
        // Score value must be between 0.00 and 100.00, if not, then throw an Error
        if (score < 0.00 || score > 100.00) {
            throw new Error("Score format is invalid! | Score must be 0.00 to 100.00\n");
        }
    }
}
