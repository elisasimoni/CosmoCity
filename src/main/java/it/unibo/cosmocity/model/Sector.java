package it.unibo.cosmocity.model;

public class Sector {

<<<<<<< Updated upstream
    private Status status;

=======
>>>>>>> Stashed changes
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

<<<<<<< Updated upstream
    public Sector() {
=======
    private final String name;

    private Status status;

    public Sector(final String name) {
        this.name = name;
>>>>>>> Stashed changes
        this.status = Status.GREEN;
    }


    public Status getStatus() {
        return this.status;
    }

    public void setStatus(final Status status) {
        this.status = status;
    }

}