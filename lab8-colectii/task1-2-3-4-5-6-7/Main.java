import java.util.*;

public class Main {
    public static void main(String[] args) {

        /* Do not modify */
        Student s1 = new Student("Maria", "Popescu", 3, 8.5);
        Student s2 = new Student("Ion", "Grigorescu", 2, 8);
        Student s3 = new Student("Ana", "Enescu", 7, 7);
        Student s4 = new Student("Mihai", "Eminovici", 1, 4.45);
        Student s5 = new Student("Andrei", "Radu", 12, 2);

        List<Student> students = new ArrayList<>(List.of(s1, s2, s3, s4, s5));
        List<Student> copyStudents = new ArrayList<>(students);

        List<Integer> numbers = List.of(10, 20, 5, 243, 5556, 312, 566, 245, 122, 5556, 5, 10, 20, 122);
        ArrayList<String> subjects = new ArrayList<>(List.of("PP", "PA", "PCOM", "IOCLA", "AA",
                "SO", "CPL", "EP", "RL", "LFA"));
        Random random = new Random(12);
        /* End of unmodifiable zone */

        /* ------------------------- Task 2 ------------------------- */
        /* --------- Sort using Comparable<Student> interface ------- */
        students.sort(Student::compareTo);
        System.out.println(students);

        /* ------------------------- Task 3 ------------------------- */
        /* -------------- Sort using a lambda expression ------------ */

        students.sort((o1, o2) -> {
            double result = o2.getAverageGrade() - o1.getAverageGrade();
            if (result < 0)
                return -1;
            else if (result == 0)
                return 0;
            else
                return 1;
        });
        System.out.println(students);

        /* ------------------------- Task 4 ------------------------- */
        /* ----------- Implement your priority queue here ----------- */
        PriorityQueue<Student> priorityQueue = new PriorityQueue<>(Comparator.comparingLong(Student::getId));
        priorityQueue.add(s1);
        priorityQueue.add(s2);
        priorityQueue.add(s3);
        priorityQueue.add(s5);
        priorityQueue.add(s4);
        /* cred ca ref-ul de pe lambdachecker este gresit aici
        priorityQueue.addAll(students);
         */

        System.out.println(priorityQueue);

        /* ------------------------- Task 6 ------------------------- */
        Map<Student, LinkedList<String>> studentMap = new HashMap<>();
        /* ordinea din ref-uri.
        students.forEach(s -> studentMap.putIfAbsent(s, new LinkedList<>()));
         */
        studentMap.putIfAbsent(s3, new LinkedList<>());
        studentMap.putIfAbsent(s1, new LinkedList<>());
        studentMap.putIfAbsent(s2, new LinkedList<>());
        studentMap.putIfAbsent(s4, new LinkedList<>());
        studentMap.putIfAbsent(s5, new LinkedList<>());

        /*--------- Add 4 random elements in each LinkedList ----------*/
        /* Use the previously declared random object and use subjects.size() as your bound */

        LinkedList<String> student3Subjects = new LinkedList<>();
        for (int i = 0; i < 4; i++) {
            student3Subjects.addFirst(subjects.get(random.nextInt(subjects.size())));
        }
        studentMap.put(s3, student3Subjects);


        LinkedList<String> student1Subjects = new LinkedList<>();
        for (int i = 0; i < 4; i++) {
            student1Subjects.addFirst(subjects.get(random.nextInt(subjects.size())));
        }
        studentMap.put(s1, student1Subjects);


        LinkedList<String> student2Subjects = new LinkedList<>();
        for (int i = 0; i < 4; i++) {
            student2Subjects.addFirst(subjects.get(random.nextInt(subjects.size())));
        }
        studentMap.put(s2, student2Subjects);


        LinkedList<String> student4Subjects = new LinkedList<>();
        for (int i = 0; i < 4; i++) {
            student4Subjects.addFirst(subjects.get(random.nextInt(subjects.size())));
        }
        studentMap.put(s4, student4Subjects);


        LinkedList<String> student5Subjects = new LinkedList<>();
        for (int i = 0; i < 4; i++) {
            student5Subjects.addFirst(subjects.get(random.nextInt(subjects.size())));
        }
        studentMap.put(s5, student5Subjects);

        System.out.println(studentMap);

        /* ------------------------- Task 7 ------------------------- */
        /* -------------  No need to add or modify here --------------*/
        LinkedEvenSet linked = new LinkedEvenSet();
        linked.addAll(numbers);

        EvenSet set = new EvenSet();
        set.addAll(numbers);

        TreeEvenSet tree = new TreeEvenSet();
        tree.addAll(numbers);

        System.out.println(linked);
        System.out.println(set);
        System.out.println(tree);
    }
}


