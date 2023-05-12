package task1;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

class Teacher {

    private static final UniversityCode UNIVERSITY_CODE = UniversityCode.POLITEHNICA_BUCHAREST;
    private final String fistName;
    private final String lastName;
    private final List<String> subjects;
    private final UUID teacherId;

    public Teacher(String fistName, String lastName, List<String> subjects, UUID teacherId) {
        this.fistName = fistName;
        this.lastName = lastName;
        this.subjects = subjects;
        this.teacherId = teacherId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher teacher = (Teacher) o;
        return Objects.equals(fistName, teacher.fistName) && Objects.equals(lastName, teacher.lastName) && Objects.equals(subjects, teacher.subjects) && Objects.equals(teacherId, teacher.teacherId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fistName, lastName, subjects, teacherId);
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "fistName='" + fistName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", subjects=" + subjects +
                '}';
    }
}
