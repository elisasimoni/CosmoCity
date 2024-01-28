package it.unibo.cosmocity.controller.view_controller;

import it.unibo.cosmocity.model.Sector;

public class SectorController {

    Sector sector;

    public SectorController(Sector sector) {
        this.sector = sector;
    }

    /**
     * @param status
     */
    public void getStatusColor(String status) {
        switch (status) {
            case "GREEN":
                this.sector.setStatus(Sector.Status.GREEN);
            case "YELLOW":
                this.sector.setStatus(Sector.Status.YELLOW);
            case "RED":
                this.sector.setStatus(Sector.Status.RED);
        }

    }

    public String setStatusColor() {
        return this.sector.getStatus().toString();

    }

}
