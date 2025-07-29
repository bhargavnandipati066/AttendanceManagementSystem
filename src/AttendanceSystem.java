import java.util.*;
import java.text.SimpleDateFormat;

public class AttendanceSystem {
    static Scanner scanner = new Scanner(System.in);
    static List<Student> studentList = new ArrayList<>();
    static List<AttendanceRecord> attendanceList = new ArrayList<>();

    public static void main(String[] args) {
        int choice;

        do {
            System.out.println("\n=== Attendance Management System ===");
            System.out.println("1. Add Student");
            System.out.println("2. Display All Students");
            System.out.println("3. Mark Attendance(for any date)");
            System.out.println("4. View Attendance");
            System.out.println("5. Attendance Summary");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine();  // consume newline

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    displayStudents();
                    break;
                case 3:
                    markAttendance();
                    break;
                case 4:
                    viewAttendance();
                    break;
                case 5:
                    viewAttendanceSummary();
                    break;
                case 6:
                    System.out.println("Exiting system. Goodbye!");
                    break;
                    default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 5);
    }

    // Method to add student
    public static void addStudent() {
        System.out.print("Enter student ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // consume newline

        // Check if ID already exists
        for (Student s : studentList) {
            if (s.getId() == id) {
                System.out.println("Student ID already exists. Please use a unique ID.");
                return;
            }
        }

        System.out.print("Enter student name: ");
        String name = scanner.nextLine();

        studentList.add(new Student(id, name));
        System.out.println("Student added successfully.");
    }

    // Method to display all students
    public static void displayStudents() {
        System.out.println("\n--- Student List ---");
        for (Student s : studentList) {
            System.out.println("ID: " + s.getId() + " | Name: " + s.getName());
        }
    }

    // Method to mark attendance
    public static void markAttendance() {
        System.out.print("Enter date for attendance (yyyy-mm-dd): ");
        String dateInput = scanner.nextLine();

        Date date;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(dateInput);
        } catch (Exception e) {
            System.out.println("Invalid date format. Try again.");
            return;
        }

        for (Student s : studentList) {
            System.out.print("Is " + s.getName() + " present? (yes/no): ");
            String input = scanner.nextLine().toLowerCase();
            boolean isPresent = input.equals("yes");
            attendanceList.add(new AttendanceRecord(s.getId(), date, isPresent));
        }

        System.out.println("Attendance marked for " + dateInput + ".");
    }

    // Method to view attendance
    public static void viewAttendance() {
        System.out.println("\n--- Attendance Records ---");
        for (AttendanceRecord record : attendanceList) {
            String studentName = "";
            for (Student s : studentList) {
                if (s.getId() == record.getStudentId()) {
                    studentName = s.getName();
                    break;
                }
            }
            System.out.println("Date: " + record.getDate() +
                    " | Name: " + studentName +
                    " | Present: " + (record.isPresent() ? "Yes" : "No"));
        }
    }
    public static void viewAttendanceSummary() {
        System.out.println("\n--- Attendance Summary ---");
        for (Student s : studentList) {
            int countPresent = 0;
            for (AttendanceRecord r : attendanceList) {
                if (r.getStudentId() == s.getId() && r.isPresent()) {
                    countPresent++;
                }
            }
            System.out.println("Student: " + s.getName() + " | Total Days Present: " + countPresent);
        }
    }

}
