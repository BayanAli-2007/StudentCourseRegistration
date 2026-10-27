import java.util.ArrayList;
import java.util.HashMap;
public class RegistrationOperations
        implements RegistrationOperationsInterface {
    private HashMap<String, ArrayList<Course>> registrations;
    public RegistrationOperations() {
        registrations = new HashMap<>();
    }
    @Override
    public boolean registerCourse(Student student, Course course) {
        if (student == null || course == null) {
            System.out.println("Student or course not found.");
            return false;
        }
        if (!course.isAvailable()) {
            System.out.println("Course is not available.");
            return false;
        }
        String id = student.getStudentId();
        if (!registrations.containsKey(id)) {
            registrations.put(id, new ArrayList<Course>());
        }
        ArrayList<Course> courses = registrations.get(id);
        for (Course c : courses) {
            if (c.getCourseCode().equals(course.getCourseCode())) {
                System.out.println("Course already registered.");
                return false;
            }
        }
        int newTotal = getTotalCreditHours(student)
                + course.getCreditHours();
        if (newTotal > student.getMaxCreditHours()) {
            System.out.println("Maximum credit hours exceeded.");
            return false;
        }
        courses.add(course);
        System.out.println("Course registered successfully.");
        return true;
    }
    @Override
    public boolean dropCourse(Student student, Course course) {
        if (student == null || course == null) {
            System.out.println("Student or course not found.");
            return false;
        }
        ArrayList<Course> courses =
                registrations.get(student.getStudentId());
        if (courses == null) {
            System.out.println("No registered courses.");
            return false;
        }
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getCourseCode()
                    .equals(course.getCourseCode())) {
                courses.remove(i);
                System.out.println("Course dropped successfully.");
                return true;
            }
        }
        System.out.println("Student is not registered in this course.");
        return false;
    }
    @Override
    public boolean changeCourseSelection(
            Student student, Course oldCourse, Course newCourse) {
        if (student == null || oldCourse == null || newCourse == null) {
            System.out.println("Invalid student or course.");
            return false;
        }
        ArrayList<Course> courses =
                registrations.get(student.getStudentId());
        if (courses == null || !courses.contains(oldCourse)) {
            System.out.println("Old course is not registered.");
            return false;
        }
        if (!newCourse.isAvailable()) {
            System.out.println("New course is not available.");
            return false;
        }
        for (Course c : courses) {
            if (c.getCourseCode().equals(newCourse.getCourseCode())) {
                System.out.println("New course is already registered.");
                return false;
            }
        }
        int newTotal = getTotalCreditHours(student)
                - oldCourse.getCreditHours()
                + newCourse.getCreditHours();
        if (newTotal > student.getMaxCreditHours()) {
            System.out.println("Maximum credit hours exceeded.");
            return false;
        }
        int index = courses.indexOf(oldCourse);
        courses.set(index, newCourse);
        System.out.println("Course selection changed successfully.");
        return true;
    }
    @Override
    public ArrayList<Course> getRegisteredCourses(Student student) {
        if (student == null) {
            return new ArrayList<Course>();
        }
        ArrayList<Course> courses =
                registrations.get(student.getStudentId());
        if (courses == null) {
            return new ArrayList<Course>();
        }
        return new ArrayList<Course>(courses);
    }
    @Override
    public int getTotalCreditHours(Student student) {
        int total = 0;
        for (Course course : getRegisteredCourses(student)) {
            total += course.getCreditHours();
        }
        return total;
    }
    @Override
    public void removeStudent(Student student) {
        if (student != null) {
            registrations.remove(student.getStudentId());
        }
    }
    @Override
    public void removeCourse(Course course) {
        if (course == null) {
            return;
        }
        for (ArrayList<Course> courses : registrations.values()) {
            courses.removeIf(c ->
                c.getCourseCode().equals(course.getCourseCode()));
        }
    }
    @Override
    public void displayRegistrationSummary(Student student) {
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }
        System.out.println("\n===== Registration Summary =====");
        student.displayInfo();
        ArrayList<Course> courses = getRegisteredCourses(student);
        if (courses.isEmpty()) {
            System.out.println("No courses registered.");
        } else {
            System.out.println("\nRegistered Courses:");
            for (Course course : courses) {
                course.displayInfo();
            }
        }
        int total = getTotalCreditHours(student);
        System.out.println("Total Credit Hours: " + total);
        if (total < student.getMinCreditHours()) {
            System.out.println("Status: Below minimum credit hours.");
        } else if (total > student.getMaxCreditHours()) {
            System.out.println("Status: Above maximum credit hours.");
        } else {
            System.out.println("Status: Credit hours are within the limit.");
        }
        System.out.println("================================");
    }
    @Override
    public void displayAllSummaries(ArrayList<Student> students) {
        if (students.isEmpty()) {
            System.out.println("No students available.");
            return;
        }
        for (Student student : students) {
            displayRegistrationSummary(student);
        }
    }
}