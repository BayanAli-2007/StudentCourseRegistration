public class GraduateStudent extends Student {
    public GraduateStudent(String studentId, String name) {
        super(studentId, name);
    }
    @Override
    public int getMinCreditHours() {
        return 6;
    }
    @Override
    public int getMaxCreditHours() {
        return 12;
    }
    @Override
    public String getStudentType() {
        return "Graduate";
    }
}