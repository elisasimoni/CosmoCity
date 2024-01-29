package it.unibo.cosmocity.model.event;

import java.util.List;
import java.util.Collections;

import it.unibo.cosmocity.controller.serialization.EventSerialization;
import it.unibo.cosmocity.controller.serialization.Serialization;
import it.unibo.cosmocity.model.settlers.Doctor;

public class EventManager {

    private List<Event> randomEvents;
    private List<Event> baseEvent;
    private Event goodEvent;
    private Serialization serialization = new EventSerialization();

    public EventManager() {
        this.randomEvents = serialization.deserialize();
        this.baseEvent = serialization.deserialize();
    }

    public RandomEvent generateRandomEvent(){

        Collections.shuffle(randomEvents);
        return new RandomEvent(randomEvents.get(0).getName(),randomEvents.get(0).getDescription(),randomEvents.get(0).getFixResources(),randomEvents.get(0).getDemageResources() );
    }

    public Event generateBaseEvent(){
        Collections.shuffle(baseEvent);
        return new BaseEvent(baseEvent.get(0).getName(),baseEvent.get(0).getDescription());
    }

    public Event generateGoodEvent(){
        return new GoodEvent(goodEvent.getName(), goodEvent.getDescription(), new Doctor());
    }

    public boolean isEmptyResource(){
        return false;
    }


}
