package by.training.enums;

import javax.management.relation.Role;

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
