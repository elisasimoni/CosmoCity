package it.unibo.cosmocity.model.sector;
/*
 * Sector class with status
 */
public class Sector {

    public enum Status {
        GREEN,
        YELLOW,
        RED
    }

    public enum SectorName {
        FARM,
        HOSPITAL,
        WORKSHOP,
        MILITARY_BASE
    }

    private Status status;

    /**
     * @return the status
     */
    public Status getStatus() {
        return this.status;
    }

    /**
     * @param status
     */
    public void setStatus(final Status status) {
        this.status = status;
    }

}