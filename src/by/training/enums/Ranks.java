package by.training.enums;

/**
 * @author Andrey Kliuchnikov
 */

public enum Ranks {
    FULL_PROFESSOR("Профессор"),
    ASSOCIATE_PROFESSOR("Доцент"),
    SENIOR_LECTURER("Старший лектор"),
    LECTURER("Лектор"),
    OTHER("Другое");

    private String name;

    private Ranks(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return Long.valueOf(ordinal());
    }
}
