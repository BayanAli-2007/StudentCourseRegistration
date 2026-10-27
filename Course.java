public class Course {
    private String courseCode;
    private String courseName;
    private int creditHours;
    private boolean available;
    public Course(String courseCode, String courseName,
                  int creditHours) {
        setCourseCode(courseCode);
        setCourseName(courseName);
        setCreditHours(creditHours);
        this.available = true;
    }
    public String getCourseCode() {
        return courseCode;
    }
    public void setCourseCode(String courseCode) {
        if (courseCode == null || courseCode.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Course code cannot be empty."
            );
        }
        this.courseCode = courseCode.trim().toUpperCase();
    }
    public String getCourseName() {
        return courseName;
    }
    public void setCourseName(String courseName) {
        if (courseName == null || courseName.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Course name cannot be empty."
            );
        }
        this.courseName = courseName.trim();
    }
    public int getCreditHours() {
        return creditHours;
    }
    public void setCreditHours(int creditHours) {
        if (creditHours < 1 || creditHours > 6) {
            throw new IllegalArgumentException(
                "Credit hours must be between 1 and 6."
            );
        }
        this.creditHours = creditHours;
    }
    public boolean isAvailable() {
        return available;
    }
    public void setAvailable(boolean available) {
        this.available = available;
    }
    public void displayInfo() {
        System.out.println(
            courseCode + " | " + courseName
            + " | Hours: " + creditHours
            + " | Available: " + available
        );
    }
}