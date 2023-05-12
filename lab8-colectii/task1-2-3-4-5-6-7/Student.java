import java.util.Objects;

public class Student implements Comparable<Student> {
    /* ------------------------- Task 1 ------------------------- */
    /* Add student properties */
    /* Generate getters and setters */
    private String name;
    private String surname;
    private long id;
    private double averageGrade;

    public Student(String name, String surname, long id, double averageGrade) {
        this.name = name;
        this.surname = surname;
        this.id = id;
        this.averageGrade = averageGrade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(double averageGrade) {
        this.averageGrade = averageGrade;
    }

    /* Override `compareTo` and `toString` methods */
    @Override
    public int compareTo(Student o) {
        if (averageGrade == o.averageGrade) {
            if (surname.equals(o.surname)) {
                return name.compareTo(o.name);
            } else {
                return surname.compareTo(o.surname);
            }
        } else {
            if (averageGrade - o.averageGrade > 0) {
                return 1;
            }
            return -1;
        }
    }

    @Override
    public String toString() {
        return "Student{name='"
                + this.name
                + "', surname='"
                + this.surname
                + "', id="
                + this.id
                + ", averageGrade="
                + this.averageGrade
                + "}";
    }

    /* ------------------------- Task 5 ------------------------- */
    /* Override `equals` and `hashCode` methods */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id && Double.compare(student.averageGrade, averageGrade) == 0 && name.equals(student.name) && surname.equals(student.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, id, averageGrade);
    }
}
