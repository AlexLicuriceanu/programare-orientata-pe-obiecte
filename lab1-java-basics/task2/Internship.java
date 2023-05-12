package task2;
import java.util.Random;

public class Internship {
    String name;
    float minGrade;
    Student[] students;

    Student chooseCandidateRandomly(Student[] students) {
        Random rand = new Random();
        int randNr = rand.nextInt(4);   // Gigel, Dorel, Marcel, Ionel
        return students[randNr];
    }

    void chooseCandidatesForInterview(Student[] students) {
        for (int i=0; i<students.length; i++) {
            if (students[i].grade >= this.minGrade) {
                System.out.println("Candidate " + students[i].name + " got a phone interview at " + this.name);
            }
        }
    }
}
