package task1;

import java.util.*;
import java.util.function.Supplier;


/*


           ECHIPA OOP vă urează un Crăciun fericit și un AN NOU fără restanțe!
                                                        *
    void *                                                           void *
                                 void *                  *        .--.
      \/ \/  \/  \/                                        ./   /=*
        \/     \/       void *      *                ...  (_____)
         \ ^ ^/                                       \ \_((^o^))-.       void *
         (o)(O)--)--------\.                           \   (   ) \  \._.
         |    |  ||================((~~~~~~~~~~~~~~~~~))|   ( )   |     \
          \__/             ,|        \. * * * * * * ./  (~~~~~~~~~~~)    \
   *        ||^||\.____./|| |          \___________/     ~||~~~~|~'\____/ *
            || ||     || || A            ||    ||          ||    |
 void *     <> <>     <> <>          (___||____||_____)   ((~~~~~|   *

*/

public class Main {

    private static final UUID coolTeacherId = UUID.randomUUID();
    private static final UUID notCoolTeacherId = UUID.randomUUID();
    private static Integer studentId = 0;
    private static Integer testNr = 0;

    private static final Supplier<Student> happyStudentSupplier = () ->
            createGenericStudent(studentId, "Happy", coolTeacherId, 10f);

    private static final Supplier<Student> sadStudentSupplier = () ->
            createGenericStudent(studentId, "Sad", notCoolTeacherId, 4.5f);

    public static void main(String[] args) {

        final Teacher coolTeacher = new Teacher(
                "Alex",
                "Amazing Teacher",
                Collections.singletonList("OOP"),
                coolTeacherId);

        final Teacher notSoCoolTeacher = new Teacher(
                "P",
                "GPL",
                Collections.singletonList("Good luck!"),
                notCoolTeacherId
        );

        final Teacher notCoolTeacher = new Teacher(
                "G.",
                "IUUU",
                Collections.singletonList("Good luck, you're going to need it!"),
                notCoolTeacherId
        );

        final List<Student> happyStudents = new ArrayList<>();
        final List<Student> sadStudents = new ArrayList<>();

        for (int i = 0; i < 10; ++i, ++studentId) {
            happyStudents.add(happyStudentSupplier.get());
            sadStudents.add(sadStudentSupplier.get());
        }

        /* TEST PART, DO NOT MODIFY IT*/

        final MultiMapValue<Teacher, Student> multiMapValue = new MultiMapValue<>();

        printTestNr(); // 1
        System.out.println(multiMapValue.isEmpty());
        System.out.println(multiMapValue.size());
        System.out.println();

        final Student firstHappyStudent = happyStudentSupplier.get();
        multiMapValue.add(coolTeacher, firstHappyStudent);
        printTestNr(); // 2
        System.out.println(multiMapValue.isEmpty());
        System.out.println(multiMapValue.size());
        System.out.println();

        final Student secondHappyStudent = happyStudentSupplier.get();
        multiMapValue.add(coolTeacher, secondHappyStudent);
        printTestNr(); // 3
        System.out.println(multiMapValue.size());
        System.out.println();

        final Student firstSadStudentNotCoolTeacher = sadStudentSupplier.get();
        multiMapValue.add(notCoolTeacher, firstSadStudentNotCoolTeacher);
        final Student firstStudentNotSoCool = sadStudentSupplier.get();
        multiMapValue.add(notSoCoolTeacher, firstStudentNotSoCool);
        printTestNr(); // 4
        System.out.println(multiMapValue.size());
        System.out.println();

        printTestNr(); // 5
        System.out.println(multiMapValue.getValues(coolTeacher).size());
        System.out.println(multiMapValue.getValues(notCoolTeacher).size());
        System.out.println(multiMapValue.getValues(notSoCoolTeacher).size());
        System.out.println();

        multiMapValue.add(coolTeacher, happyStudentSupplier.get());
        multiMapValue.add(notCoolTeacher, sadStudentSupplier.get());
        multiMapValue.add(notSoCoolTeacher, sadStudentSupplier.get());
        multiMapValue.add(coolTeacher, happyStudentSupplier.get());
        printTestNr(); // 6
        System.out.println(multiMapValue.getValues(coolTeacher).size());
        System.out.println(multiMapValue.getValues(notCoolTeacher).size());
        System.out.println(multiMapValue.getValues(notSoCoolTeacher).size());
        System.out.println();

        printTestNr(); // 7
        System.out.println(multiMapValue.containsKey(coolTeacher));
        System.out.println(multiMapValue.containsKey(notCoolTeacher));
        System.out.println(multiMapValue.containsKey(notSoCoolTeacher));
        System.out.println();

        printTestNr(); // 8
        System.out.println(multiMapValue.getFirst(coolTeacher).equals(firstHappyStudent));
        System.out.println(multiMapValue.getFirst(notCoolTeacher).equals(firstSadStudentNotCoolTeacher));
        System.out.println(multiMapValue.getFirst(notSoCoolTeacher).equals(firstStudentNotSoCool));
        System.out.println();

        printTestNr(); // 9
        final List<Student> removedStudents = multiMapValue.remove(notSoCoolTeacher);
        removedStudents.forEach(student -> System.out.println(sadStudents.contains(student)));
        System.out.println(multiMapValue.size());
        System.out.println(multiMapValue.getFirst(notSoCoolTeacher));
        System.out.println();

        printTestNr(); // 10
        System.out.println(multiMapValue.getValues(coolTeacher).size());
        multiMapValue.addAll(coolTeacher, happyStudents);
        System.out.println(multiMapValue.getValues(coolTeacher).size());
        System.out.println(multiMapValue.getValues(notCoolTeacher).size());
        System.out.println();

        printTestNr(); // 11
        final MultiMapValue<Teacher, Student> addAllMultiMapValue1 = new MultiMapValue<>();
        addAllMultiMapValue1.addAll(notSoCoolTeacher, sadStudents);
        multiMapValue.addAll(addAllMultiMapValue1);
        System.out.println(multiMapValue.size());
        System.out.println(multiMapValue.getValues(coolTeacher).size());
        System.out.println(multiMapValue.getValues(notSoCoolTeacher).size());
        System.out.println(multiMapValue.getValues(notCoolTeacher).size());
        System.out.println();

        printTestNr(); // 12
        final List<Student> notSoCoolTeacherStudentsList = multiMapValue.getValues(notSoCoolTeacher);
        System.out.println(notSoCoolTeacherStudentsList.size());
        System.out.println(notSoCoolTeacherStudentsList.size() == sadStudents.size());
        System.out.println(notSoCoolTeacherStudentsList.containsAll(sadStudents));
        System.out.println();

        printTestNr(); // 13
        System.out.println();
        System.out.println(coolTeacher);
        multiMapValue.getValues(coolTeacher).forEach(System.out::println);
        System.out.println();
        System.out.println(notSoCoolTeacher);
        multiMapValue.getValues(notSoCoolTeacher).forEach(System.out::println);
        System.out.println();
        System.out.println(notCoolTeacher);
        multiMapValue.getValues(notCoolTeacher).forEach(System.out::println);

    }

    private static void printTestNr() {
        System.out.println("TEST " + ++testNr + " result:");
    }

    private static Student createGenericStudent(final Integer studentNr, final String firstName,
                                                final UUID teacherId, final Float grade) {
        return new Student(
                firstName + " " + studentNr,
                "Human Being",
                18,
                grade,
                teacherId);
    }
}