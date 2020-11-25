package by.training.domain;

import by.training.enums.Ranks;

/**
 * @author Andrey Kliuchnikov
 */

public class Instructor extends Entity {
    private String surname;
    private String name;
    private Ranks rank;

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

    public Ranks getRank() {
        return rank;
    }

    public void setRank(Ranks rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "Instructor{" +
                "id=" + getId() +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", rank=" + rank +
                '}';
    }
}
