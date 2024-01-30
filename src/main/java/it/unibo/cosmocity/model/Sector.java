package it.unibo.cosmocity.model;

public class Sector {

    public enum Status {
        GREEN,
        YELLOW,
        RED
    }

    public enum SectorName {
        FARM,
        HOSPITAL,
        MANUFACTORY,
        MILITARY_BASE
    }

    private Status status;

    public Sector() {

        this.status = Status.GREEN;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(final Status status) {
        this.status = status;
    }

}