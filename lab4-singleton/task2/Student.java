import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Student {
    private String firstName;
    private String lastName;
    private Map<String, Integer> subjects;

    public Student(String firstName, String lastName, Map<String, Integer> subjects) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.subjects = subjects;
    }

    // TODO Task 2.b.II
    public Student(Student student) {
        this.firstName = new String(student.firstName);
        this.lastName = new String(student.lastName);
        this.subjects = new HashMap<>(student.subjects);
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

    public Map<String, Integer> getSubjects() {
        return subjects;
    }

    public void setSubjects(HashMap<String, Integer> subjects) {
        this.subjects = subjects;
    }

    public double averageGrade() {
        // TODO Task 2.b.I
        int count = 0;
        double avg = 0;

        for(Integer value : subjects.values()) {
            avg += value;
            count++;
        }

        if (count != 0)
            return avg/count;
        return 0;
    }

    public List<Teacher> getAllTeachers() {
        // TODO Task 2.f.I
        return Collections.unmodifiableList(Database.getDatabase().findAllTeachers());
    }

    public int getGradeForSubject(String subject) {
        // TODO Task 2.b.III
        for(Map.Entry<String, Integer> entry : subjects.entrySet()) {
            if (subject.equals(entry.getKey())) {
                return entry.getValue();
            }
        }
        return 0;
    }

    @Override
    public String toString() {
        return "Student: " + firstName + " " + lastName + "\n"
                + "Subjects: " + subjects + "\n";
    }

    public List<Teacher> getTeachersBySubject(String subject) {
        // TODO Task 2.f.II
        return Collections.unmodifiableList(Database.getDatabase().findTeachersBySubject(subject));
    }

    public List<Student> getAllStudents() {
        // TODO Task 2.f.III
        return Collections.unmodifiableList(Database.getDatabase().findAllStudents());
    }

    public List<Student> getStudentsBySubject(String subject) {
        // TODO Task 2.f.IV
        return Collections.unmodifiableList(Database.getDatabase().getStudentsBySubject(subject));
    }

    public List<Student> getStudentsByAverageGrade() {
        // TODO Task 2.f.V
        return Collections.unmodifiableList(Database.getDatabase().getStudentsByAverageGrade());
    }

    public List<Student> getStudentsByGradeForSubject(String subject) {
        // TODO Task 2.f.VI
        return Collections.unmodifiableList(Database.getDatabase().getStudentsByGradeForSubject(subject));
    }
}
