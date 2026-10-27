import java.util.ArrayList;
import java.util.Scanner;
public class StudentManagementSystem {
    private static Scanner input = new Scanner(System.in);
    private static ArrayList<Student> students = new ArrayList<>();
    private static ArrayList<Course> courses = new ArrayList<>();
    private static RegistrationOperations operations =
            new RegistrationOperations();
    public static void main(String[] args) {
        int choice;
        do {
            displayMenu();
            choice = readInt("Enter your choice: ");
            try {
                switch (choice) {
                    case 1:
                        addStudent();
                        break;
                    case 2:
                        addCourse();
                        break;
                    case 3:
                        registerStudent();
                        break;
                    case 4:
                        dropCourse();
                        break;
                    case 5:
                        displayAllStudents();
                        break;
                    case 6:
                        displayAllCourses();
                        break;
                    case 7:
                        showSummary();
                        break;
                    case 8:
                        searchStudent();
                        break;
                    case 9:
                        modifyStudentName();
                        break;
                    case 10:
                        modifyCourseDetails();
                        break;
                    case 11:
                        changeCourseSelection();
                        break;
                    case 12:
                        deleteStudent();
                        break;
                    case 13:
                        changeCourseAvailability();
                        break;
                    case 14:
                        operations.displayAllSummaries(students);
                        break;
                    case 15:
                        deleteCourse();
                        break;
                    case 0:
                        System.out.println("Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (choice != 0);
        input.close();
    }
    private static void displayMenu() {
        System.out.println("\n===== Student Course Registration =====");
        System.out.println("1. Add Student");
        System.out.println("2. Add Course");
        System.out.println("3. Register Student in Course");
        System.out.println("4. Drop Course");
        System.out.println("5. Display All Students");
        System.out.println("6. Display All Courses");
        System.out.println("7. Registration Summary");
        System.out.println("8. Search Student");
        System.out.println("9. Modify Student Name");
        System.out.println("10. Modify Course Details");
        System.out.println("11. Change Course Selection");
        System.out.println("12. Delete Student");
        System.out.println("13. Change Course Availability");
        System.out.println("14. Report for All Students");
        System.out.println("15. Delete Course");
        System.out.println("0. Exit");
    }
    private static int readInt(String message) {
        while (true) {
            System.out.print(message);
            String value = input.nextLine().trim();
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
    private static String readText(String message) {
        System.out.print(message);
        return input.nextLine().trim();
    }
    private static Student findStudent(String id) {
        for (Student student : students) {
            if (student.getStudentId().equals(id)) {
                return student;
            }
        }
        return null;
    }
    private static Course findCourse(String code) {
        for (Course course : courses) {
            if (course.getCourseCode().equalsIgnoreCase(code.trim())) {
                return course;
            }
        }
        return null;
    }
    private static void addStudent() {
        String id = readText("Enter 9-digit student ID: ");
        if (!id.matches("\\d{9}")) {
            System.out.println("Invalid ID. Student ID must contain 9 digits.");
            return;
        }
        if (findStudent(id) != null) {
            System.out.println("Student ID already exists.");
            return;
        }
        String name = readText("Enter student name: ");
        System.out.println("1. Undergraduate");
        System.out.println("2. Graduate");
        int type = readInt("Choose student type: ");
        Student student;
        if (type == 1) {
            student = new UndergraduateStudent(id, name);
        } else if (type == 2) {
            student = new GraduateStudent(id, name);
        } else {
            System.out.println("Invalid student type.");
            return;
        }
        students.add(student);
        System.out.println("Student added successfully.");
    }
    private static void addCourse() {
        String code = readText("Enter course code: ");
        if (findCourse(code) != null) {
            System.out.println("Course code already exists.");
            return;
        }
        String name = readText("Enter course name: ");
        int hours = readInt("Enter credit hours (1-6): ");
        Course course = new Course(code, name, hours);
        courses.add(course);
        System.out.println("Course added successfully.");
    }
    private static void registerStudent() {
        String id = readText("Enter student ID: ");
        Student student = findStudent(id);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }
        String code = readText("Enter course code: ");
        Course course = findCourse(code);
        if (course == null) {
            System.out.println("Course not found.");
            return;
        }
        operations.registerCourse(student, course);
    }
    private static void dropCourse() {
        Student student = findStudent(readText("Enter student ID: "));
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }
        Course course = findCourse(readText("Enter course code: "));
        if (course == null) {
            System.out.println("Course not found.");
            return;
        }
        operations.dropCourse(student, course);
    }
    private static void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students available.");
            return;
        }
        for (Student student : students) {
            System.out.println("--------------------");
            student.displayInfo();
        }
    }
    private static void displayAllCourses() {
        if (courses.isEmpty()) {
            System.out.println("No courses available.");
            return;
        }
        for (Course course : courses) {
            course.displayInfo();
        }
    }
    private static void showSummary() {
        Student student = findStudent(
                readText("Enter student ID: "));
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }
        operations.displayRegistrationSummary(student);
    }
    private static void searchStudent() {
        Student student = findStudent(
                readText("Enter student ID: "));
        if (student == null) {
            System.out.println("Student not found.");
        } else {
            student.displayInfo();
            operations.displayRegistrationSummary(student);
        }
    }
    private static void modifyStudentName() {
        Student student = findStudent(
                readText("Enter student ID: "));
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }
        String newName = readText("Enter new name: ");
        student.setName(newName);
        System.out.println("Student name updated successfully.");
    }
    private static void modifyCourseDetails() {
        Course course = findCourse(
                readText("Enter course code: "));
        if (course == null) {
            System.out.println("Course not found.");
            return;
        }
        String newName = readText("Enter new course name: ");
        int newHours = readInt("Enter new credit hours (1-6): ");
        if (newName.isEmpty()) {
            System.out.println("Course name cannot be empty.");
            return;
        }
        if (newHours < 1 || newHours > 6) {
            System.out.println("Credit hours must be between 1 and 6.");
            return;
        }
        int oldHours = course.getCreditHours();
        String oldName = course.getCourseName();
        course.setCourseName(newName);
        course.setCreditHours(newHours);
        for (Student student : students) {
            if (operations.getRegisteredCourses(student).contains(course)) {
                int total = operations.getTotalCreditHours(student);
                if (total > student.getMaxCreditHours()) {
                    course.setCourseName(oldName);
                    course.setCreditHours(oldHours);
                    System.out.println(
                        "Update cancelled: a student would exceed "
                        + "the maximum credit hours."
                    );
                    return;
                }
            }
        }
        System.out.println("Course details updated successfully.");
    }
    private static void changeCourseSelection() {
        Student student = findStudent(
                readText("Enter student ID: "));
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }
        Course oldCourse = findCourse(
                readText("Enter current course code: "));
        Course newCourse = findCourse(
                readText("Enter new course code: "));
        if (oldCourse == null || newCourse == null) {
            System.out.println("Course not found.");
            return;
        }
        operations.changeCourseSelection(
                student, oldCourse, newCourse);
    }
    private static void deleteStudent() {
        Student student = findStudent(
                readText("Enter student ID: "));
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }
        operations.removeStudent(student);
        students.remove(student);
        System.out.println("Student deleted successfully.");
    }
    private static void changeCourseAvailability() {
        Course course = findCourse(
                readText("Enter course code: "));
        if (course == null) {
            System.out.println("Course not found.");
            return;
        }
        course.setAvailable(!course.isAvailable());
        System.out.println("Course availability is now: "
                + course.isAvailable());
    }
    private static void deleteCourse() {
        Course course = findCourse(
                readText("Enter course code: "));
        if (course == null) {
            System.out.println("Course not found.");
            return;
        }
        operations.removeCourse(course);
        courses.remove(course);
        System.out.println("Course deleted successfully.");
    }
}