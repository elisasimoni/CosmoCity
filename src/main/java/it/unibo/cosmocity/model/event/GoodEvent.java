package it.unibo.cosmocity.model.event;

import it.unibo.cosmocity.model.settlers.BaseSettler;

public class GoodEvent extends Event {
    private BaseSettler settler;

    public GoodEvent(String name, String description, BaseSettler settler) {
        super(name, description);
        this.settler = settler;
    }
}
