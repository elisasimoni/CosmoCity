package it.unibo.cosmocity.model.event;

import it.unibo.cosmocity.model.settlers.BaseSettler;

public class GoodEvent extends Event {
    private final BaseSettler settler;

    /**
     * @param name
     * @param description
     * @param settler
     *                    Create a good event like a new settler
     */
    public GoodEvent(final String name, final String description, final BaseSettler settler) {
        super(name, description);
        this.settler = settler;
    }

    /**
     * @return the settler
     */
    public BaseSettler getSettler() {
        return settler;
    }
}
