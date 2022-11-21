package utec.common.events;

import java.util.EventObject;

public interface EventMaker
{
    <T extends EventObject> void addEventHandler(EventType eventType, EventHandler<T> eventHandler);
    <T extends EventObject> void removeEventHandler(EventType eventType, EventHandler<T> eventHandler);
}
