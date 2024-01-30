package it.unibo.cosmocity.model;

public class Sector {

    private Status status;

    public enum Status {
        GREEN,
        YELLOW,
        RED
    }

    public Sector() {
        this.status = Status.GREEN;
    }


    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}