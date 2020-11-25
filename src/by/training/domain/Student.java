package by.training.domain;

/**
 * @author Andrey Kliuchnikov
 */

public class Student extends Entity {
    private String surname;
    private String name;
    private int studyYear;

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStudyYear() {
        return studyYear;
    }

    public void setStudyYear(int studyYear) {
        this.studyYear = studyYear;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + getId() +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", studyYear=" + studyYear +
                '}';
    }
}
