import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Database {
    private final List<Student> students = new ArrayList<>();
    private final List<Teacher> teachers = new ArrayList<>();
    private static int counter;

    // TODO: Task 2.a
    private static Database database = null;
    private Database() {}

    public static Database getDatabase() {
        if (database == null) {
            database = new Database();
            Database.counter++;
        }
        return database;
    }

    public static int getNumberOfInstances() {
        // TODO Task 2.g
        return counter;
    }

    public void addTeachers(List<Teacher> teachers) {
        this.teachers.addAll(teachers);
    }

    public void addStudents(List<Student> students) {
        this.students.addAll(students);
    }

    public List<Teacher> findTeachersBySubject(String subject) {
        // TODO 2.d.IV
        List<Teacher> subjectTeachers = new ArrayList<>();
        List<Teacher> allTeachers = this.findAllTeachers();

        for(Teacher t : allTeachers) {
            if (t.getSubjects().contains(subject)) {
                subjectTeachers.add(new Teacher(t));
            }
        }
        return subjectTeachers;
    }

    public List<Student> findAllStudents() {
        // TODO Task 2.d.I
        return this.students;
    }

    public List<Teacher> findAllTeachers() {
        // TODO Task 2.d.II
        return this.teachers;
    }

    public List<Student> getStudentsBySubject(String subject) {
        // TODO Task 2.d.III
        List<Student> subjectStudents = new ArrayList<>();
        List<Student> allStudents = this.findAllStudents();

        for(Student s : allStudents) {
            if (s.getSubjects().containsKey(subject)) {
                subjectStudents.add(new Student(s));
            }
        }
        return subjectStudents;
    }

    public List<Student> getStudentsByAverageGrade() {
        // TODO Task 2.d.V
        List<Student> bestStudents = new ArrayList<>(this.findAllStudents());
        bestStudents.sort(Comparator.comparing(Student::averageGrade).thenComparing(Student::getFirstName));
        return bestStudents;
    }

    public List<Student> getStudentsByGradeForSubject(String subject) {
        // TODO Task 2.d.VI
        List<Student> bestStudentsSubject = new ArrayList<>(this.getStudentsBySubject(subject));
        bestStudentsSubject.sort(Comparator.comparing(Student::averageGrade).thenComparing(Student::getFirstName));
        return bestStudentsSubject;
    }
}