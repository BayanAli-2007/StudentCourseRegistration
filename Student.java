public abstract class Student {
    private String studentId;
    private String name;
    public Student(String studentId, String name) {
        setStudentId(studentId);
        setName(name);
    }
    public String getStudentId() {
        return studentId;
    }
    public void setStudentId(String studentId) {
        if (studentId == null || !studentId.matches("\\d{9}")) {
            throw new IllegalArgumentException(
                "Student ID must contain 9 digits."
            );
        }
        this.studentId = studentId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Name cannot be empty."
            );
        }
        this.name = name.trim();
    }
    public abstract int getMinCreditHours();
    public abstract int getMaxCreditHours();
    public abstract String getStudentType();
    public void displayInfo() {
        System.out.println("Student ID: " + studentId);
        System.out.println("Name: " + name);
        System.out.println("Type: " + getStudentType());
        System.out.println("Credit Hours: " + getMinCreditHours()
                + " - " + getMaxCreditHours());
    }
}