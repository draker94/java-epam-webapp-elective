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
