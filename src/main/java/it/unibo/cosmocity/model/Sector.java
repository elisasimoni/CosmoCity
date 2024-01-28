package it.unibo.cosmocity.model;

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

    public void setStatus(Status status) {
        this.status = status;
    }

}