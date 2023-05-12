package task1;

import java.util.Objects;
import java.util.UUID;

class Student {

    private final String fistName;
    private final String lastName;
    private final Integer age;
    private final Float grade;
    private final UUID teacherId;

    public Student(String fistName, String lastName, Integer age, Float grade, UUID teacherId) {
        this.fistName = fistName;
        this.lastName = lastName;
        this.age = age;
        this.grade = grade;
        this.teacherId = teacherId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(fistName, student.fistName) && Objects.equals(lastName, student.lastName) && Objects.equals(age, student.age) && Objects.equals(grade, student.grade) && Objects.equals(teacherId, student.teacherId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fistName, lastName, age, grade, teacherId);
    }

    @Override
    public String toString() {
        return "Student{" +
                "fistName='" + fistName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", grade=" + grade +
                '}';
    }
}
