package utec.common.events;

import java.util.*;

public class EventMakerImpl implements EventMaker
{
    Map<EventType, List<EventHandler>> eventHandlers = new HashMap<EventType, List<EventHandler>>();

    public <TE extends EventObject> void handleEvent(EventType eventType, TE event)
    {
        List<EventHandler> typedHandlers = eventHandlers.get(eventType);
        if (typedHandlers != null)
        {
            EventHandler[] ehs = new EventHandler[typedHandlers.size()];
            typedHandlers.toArray(ehs);
            for (EventHandler eh : ehs)
            {
                eh.handle(event);
            }
        }
    }

    @Override
    public <T extends EventObject> void addEventHandler(EventType eventType, EventHandler<T> eventHandler)
    {
        List<EventHandler> typedHandlers = eventHandlers.get(eventType);
        if (typedHandlers == null)
        {
            typedHandlers = new ArrayList<EventHandler>();
            eventHandlers.put(eventType, typedHandlers);
        }
        typedHandlers.add(eventHandler);
    }

    @Override
    public <T extends EventObject> void removeEventHandler(EventType eventType, EventHandler<T> eventHandler)
    {
        List<EventHandler> typedHandlers = eventHandlers.get(eventType);
        if (typedHandlers != null)
        {
            typedHandlers.remove(eventHandler);
            if (typedHandlers.isEmpty())
            {
                eventHandlers.remove(typedHandlers);
            }
        }
    }
}
