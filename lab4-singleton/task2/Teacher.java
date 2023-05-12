import java.util.ArrayList;
import java.util.List;

public class Teacher {
    private String firstName;
    private String lastName;
    private List<String> subjects;

    public Teacher(String firstName, String lastName, List<String> subjects) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.subjects = subjects;
    }

    // TODO Task 2.c
    public Teacher(Teacher teacher) {
        this.firstName = new String(teacher.firstName);
        this.lastName = new String(teacher.lastName);
        this.subjects = new ArrayList<>(teacher.subjects);
    }

    @Override
    public String toString() {
        return "Teacher: " + firstName + " " + lastName + "\n"
                + "Subjects: " + subjects + "\n";
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<String> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<String> subjects) {
        this.subjects = subjects;
    }

    public List<Teacher> getAllTeachers() {
        // TODO Task 2.e.I
        return Database.getDatabase().findAllTeachers();
    }

    public List<Teacher> getTeachersBySubject(String subject) {
        // TODO Task 2.e.II
        return Database.getDatabase().findTeachersBySubject(subject);
    }

    public List<Student> getAllStudents() {
        // TODO Task 2.e.III
        return Database.getDatabase().findAllStudents();
    }

    public List<Student> getStudentsBySubject(String subject) {
        // TODO Task 2.e.IV
        return Database.getDatabase().getStudentsBySubject(subject);
    }

    public List<Student> getStudentsByAverageGrade() {
        // TODO Task 2.e.V
        return Database.getDatabase().getStudentsByAverageGrade();
    }

    public List<Student> getStudentsByGradeForSubject(String subject) {
        // TODO Task 2.e.VI
        return Database.getDatabase().getStudentsByGradeForSubject(subject);
    }
}
