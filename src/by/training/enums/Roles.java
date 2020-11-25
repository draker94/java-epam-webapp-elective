package by.training.enums;

/**
 * @author Andrey Kliuchnikov
 */

public enum Roles {
    ADMINISTRATOR("Одмен"),
    INSTRUCTOR("Препод"),
    STUDENT("Студент");

    private String name;

    private Roles(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return Long.valueOf(ordinal());
    }
}
