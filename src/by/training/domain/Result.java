package by.training.domain;

import java.time.LocalDate;

/**
 * @author Andrey Kliuchnikov
 */

public class Result extends Entity {
    private Assignment assignment;
    private int mark;
    private String review;
    private LocalDate date;

    public Assignment getAssignment() {
        return assignment;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Result)) return false;

        Result result = (Result) o;

        if (mark != result.mark) return false;
        if (assignment != null ? !assignment.equals(result.assignment) : result.assignment != null) return false;
        if (review != null ? !review.equals(result.review) : result.review != null) return false;
        return date != null ? date.equals(result.date) : result.date == null;
    }

    @Override
    public int hashCode() {
        int result = assignment != null ? assignment.hashCode() : 0;
        result = 31 * result + mark;
        result = 31 * result + (review != null ? review.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Result{" +
                "id=" + getId() +
                ", assignment=" + assignment +
                ", mark=" + mark +
                ", review='" + review + '\'' +
                ", date=" + date +
                '}';
    }
}
