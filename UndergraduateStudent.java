public class UndergraduateStudent extends Student {
    public UndergraduateStudent(String studentId, String name) {
        super(studentId, name);
    }
    @Override
    public int getMinCreditHours() {
        return 9;
    }
    @Override
    public int getMaxCreditHours() {
        return 18;
    }
    @Override
    public String getStudentType() {
        return "Undergraduate";
    }
}