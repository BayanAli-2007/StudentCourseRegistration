import java.util.ArrayList;
public interface RegistrationOperationsInterface {
    boolean registerCourse(Student student, Course course);
    boolean dropCourse(Student student, Course course);
    boolean changeCourseSelection(
        Student student, Course oldCourse, Course newCourse
    );
    ArrayList<Course> getRegisteredCourses(Student student);
    int getTotalCreditHours(Student student);
    void removeStudent(Student student);
    void removeCourse(Course course);
    void displayRegistrationSummary(Student student);
    void displayAllSummaries(ArrayList<Student> students);
}