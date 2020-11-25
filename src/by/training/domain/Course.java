package by.training.domain;

/**
 * @author Andrey Kliuchnikov
 */

public class Course extends Entity {
    private String name;
    private int hours;
    private String description;
    private Instructor instructor;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + getId() +
                ", name='" + name + '\'' +
                ", hours=" + hours +
                ", description='" + description + '\'' +
                ", instructor=" + instructor +
                '}';
    }
}
