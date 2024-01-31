package it.unibo.cosmocity.model.event;

import java.util.Collections;
import java.util.List;

import it.unibo.cosmocity.controller.serialization.EventSerialization;
import it.unibo.cosmocity.model.settlers.BaseSettler;
/*
 * Clase use to handle the events
 * 
 * 
 */
public class EventManager {

    private final List<Event> randomEvents;
    private List<Event> baseEvent;
    private Event goodEvent;
    private final EventSerialization serialization = new EventSerialization();

    /**
     *  Create an event manager
     */
    public EventManager() {
        this.randomEvents = serialization.deserialize();
    }

    /**
     * @return a random event from the list
     */
    public RandomEvent generateRandomEvent(){

        Collections.shuffle(randomEvents);
        return new RandomEvent(randomEvents.get(0).getName(),randomEvents.get(0).getDescription(),randomEvents.get(0).getFixResources(),randomEvents.get(0).getDemageResources() );
    }

    /**
     * @return a base event
     */
    public Event generateBaseEvent(){
        Collections.shuffle(baseEvent);
        return new BaseEvent(baseEvent.get(0).getName(),baseEvent.get(0).getDescription());
    }

    /**
     * @param baseSettler
     * @return a good event with a new settler
     */
    public GoodEvent generateGoodEvent(final BaseSettler baseSettler){
        return new GoodEvent(goodEvent.getName(), goodEvent.getDescription(), baseSettler);
    }

    /**
     * @return  true if the resource is empty
     */
    public boolean isEmptyResource(){
        return false;
    }


}
