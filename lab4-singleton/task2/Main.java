import javax.xml.crypto.Data;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int task = scanner.nextInt();

        // adding people to database
        Helpers.addTeachers();
        Helpers.addStudents();

        // tests
        var testStudent = Database.getDatabase().findAllStudents().get(0);
        var testTeacher = Database.getDatabase().findAllTeachers().get(0);

        switch(task) {
            case 1:
                System.out.println(testStudent.getFirstName());
                System.out.println(testTeacher.getFirstName());
                break;

            case 2:
                System.out.println(testStudent.averageGrade());
                System.out.println(testStudent.getGradeForSubject("Programming Paradigms"));
                break;

            case 3:
                var testTeacherCopy = new Teacher(testTeacher);
                System.out.println(testTeacherCopy);
                break;

            case 4:
                for (Student student : Database.getDatabase().findAllStudents()) {
                    System.out.println(student.getFirstName() + " " + student.getLastName());
                }
                System.out.println("------------------------------");
                for (Teacher teacher : Database.getDatabase().findAllTeachers()) {
                    System.out.println(teacher.getFirstName() + " " + teacher.getLastName());
                }
                System.out.println("------------------------------");
                for (Student student : Database.getDatabase().getStudentsBySubject("Programming Paradigms")) {
                    System.out.println(student.getFirstName() + " " + student.getLastName());
                }
                System.out.println("------------------------------");
                for (Teacher teacher : Database.getDatabase().findTeachersBySubject("Data Structures")) {
                    System.out.println(teacher.getFirstName() + " " + teacher.getLastName());
                }
                System.out.println("------------------------------");
                for (Student student : Database.getDatabase().getStudentsByAverageGrade()) {
                    System.out.println(student.getFirstName() + " " + student.getLastName());
                }
                System.out.println("------------------------------");
                for (Student student : Database.getDatabase().getStudentsByGradeForSubject("Programming Paradigms")) {
                    System.out.println(student.getFirstName() + " " + student.getLastName());
                }
                break;

            case 5:
                System.out.println("TESTING TEACHER FUNCTIONALITIES:\n");

                for (Teacher teacher : testTeacher.getAllTeachers()) {
                    System.out.println(teacher.getFirstName() + " " + teacher.getLastName());
                }
                System.out.println("------------------------------");
                for (Teacher teacher : testTeacher.getTeachersBySubject("Programming Paradigms")) {
                    System.out.println(teacher.getFirstName() + " " + teacher.getLastName());
                }
                System.out.println("------------------------------");
                for (Teacher teacher : testTeacher.getTeachersBySubject("Data Structures")) {
                    System.out.println(teacher.getFirstName() + " " + teacher.getLastName());
                }
                System.out.println("------------------------------");
                for (Student student : testTeacher.getAllStudents()) {
                    System.out.println(student.getFirstName() + " " + student.getLastName());
                }

                System.out.println("\nTESTING MUTABILITY FOR TEACHERS:\n");

                testTeacher.getAllStudents().get(0).setFirstName("Malone");
                for (Student student : testTeacher.getAllStudents()) {
                    System.out.println(student.getFirstName() + " " + student.getLastName());
                }
                System.out.println("------------------------------");
                for (Student student : testTeacher.getStudentsBySubject("Programming Paradigms")) {
                    System.out.println(student.getFirstName() + " " + student.getLastName());
                }
                for (Student student : testTeacher.getStudentsByAverageGrade()) {
                    System.out.println(student.getFirstName() + " " + student.getLastName());
                }
                System.out.println("------------------------------");
                for (Student student : testTeacher.getStudentsByGradeForSubject("Programming Paradigms")) {
                    System.out.println(student.getFirstName() + " " + student.getLastName());
                }
                System.out.println("------------------------------");
                for (Student student : testTeacher.getStudentsByGradeForSubject("Computers Programming")) {
                    System.out.println(student.getFirstName() + " " + student.getLastName()); //  + " " + student.getGradeForSubject("Computers Programming")
                }
                System.out.println("------------------------------");
                for (Student student : testTeacher.getStudentsByGradeForSubject("Assembly Language Programming")) {
                    System.out.println(student.getFirstName() + " " + student.getLastName()); // + " " + student.getGradeForSubject("Assembly Language Programming")
                }
                break;

            case 6:
                System.out.println("TESTING STUDENTS FUNCTIONALITIES:\n");

                for (Teacher teacher : testStudent.getAllTeachers()) {
                    System.out.println(teacher.getFirstName() + " " + teacher.getLastName());
                }
                System.out.println("------------------------------");
                for (Teacher teacher : testStudent.getTeachersBySubject("Programming Paradigms")) {
                    System.out.println(teacher.getFirstName() + " " + teacher.getLastName());
                }
                System.out.println("------------------------------");
                for (Teacher teacher : testStudent.getTeachersBySubject("Data Structures")) {
                    System.out.println(teacher.getFirstName() + " " + teacher.getLastName());
                }
                System.out.println("------------------------------");
                for (Student student : testStudent.getAllStudents()) {
                    System.out.println(student.getFirstName() + " " + student.getLastName());
                }
                break;

            case 7:
                try {
                    // testing immutability for students
                    testStudent.getAllStudents().get(0).setFirstName("Ginel");

                    // it should throw an error if getAllStudents() from Student is completely immutable
                    testStudent.getAllStudents().add(new Student(testStudent));
                }
                catch(Exception e) {
                    System.out.println("Students immutability test passed!!! :]");
                }
                break;

            case 8:
                System.out.println(Database.getNumberOfInstances());
                break;
        }

    }
}