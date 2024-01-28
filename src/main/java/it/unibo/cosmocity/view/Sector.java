package it.unibo.cosmocity.view;

public class Sector {

    private String name;
    private Status status;

    public enum Status {
        GREEN,
        YELLOW,
        RED
    }

    public Sector(String name) {
        this.name = name;
        this.status = Status.GREEN;
    }

    public String getName() {
        return name;
    }

    public Status getStatus() {
        return this.status;
    }

    


}
