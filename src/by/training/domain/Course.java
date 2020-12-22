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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Course)) return false;
        if (!super.equals(o)) return false;

        Course course = (Course) o;

        if (hours != course.hours) return false;
        if (name != null ? !name.equals(course.name) : course.name != null) return false;
        if (description != null ? !description.equals(course.description) : course.description != null) return false;
        return instructor != null ? instructor.equals(course.instructor) : course.instructor == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + hours;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (instructor != null ? instructor.hashCode() : 0);
        return result;
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
