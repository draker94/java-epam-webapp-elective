package by.training.domain;

import java.time.LocalDate;

/**
 * @author Andrey Kliuchnikov
 */

public class Assignment extends Entity {
    private Student student;
    private Course course;
    private LocalDate beginDate;
    private LocalDate endDate;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public LocalDate getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(LocalDate beginDate) {
        this.beginDate = beginDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Assignment{" +
                "id=" + getId() +
                ", student=" + student +
                ", course=" + course +
                ", beginDate=" + beginDate +
                ", endDate=" + endDate +
                '}';
    }
}
