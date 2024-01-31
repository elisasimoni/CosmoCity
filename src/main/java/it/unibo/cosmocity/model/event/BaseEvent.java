package it.unibo.cosmocity.model.event;

/*
 * Class used to create the generic event
 */
public class BaseEvent extends Event {
    /**
     * @param name
     * @param description
     *  Create a base event
     */
    public BaseEvent(String name, String description) {
        super(name, description);
    }
}
