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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Assignment)) return false;

        Assignment that = (Assignment) o;

        if (student != null ? !student.equals(that.student) : that.student != null) return false;
        if (course != null ? !course.equals(that.course) : that.course != null) return false;
        if (beginDate != null ? !beginDate.equals(that.beginDate) : that.beginDate != null) return false;
        return endDate != null ? endDate.equals(that.endDate) : that.endDate == null;
    }

    @Override
    public int hashCode() {
        int result = student != null ? student.hashCode() : 0;
        result = 31 * result + (course != null ? course.hashCode() : 0);
        result = 31 * result + (beginDate != null ? beginDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        return result;
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
