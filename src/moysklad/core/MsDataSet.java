package moysklad.core;

import moysklad.entities.MsEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MsDataSet implements Serializable
{
    class SyncKey implements Serializable {}
    private final SyncKey syncKey = new SyncKey();

    Map<Class<? extends MsEntity>, List<? extends MsEntity>> entitiesMap = new HashMap<>();

    public <T extends MsEntity> List<T> getEntities(Class<T> entityClass)
    {
        List<T> result = (List<T>)entitiesMap.get(entityClass);
        if(result == null)
        {
            synchronized (syncKey)
            {
                result = new ArrayList<>();
                entitiesMap.put(entityClass, result);
            }
        }
        return result;
    }

    public <T extends MsEntity> void addEntity(T entity)
    {
        synchronized (syncKey)
        {
            List<T> entities = (List<T>)getEntities(entity.getClass());
            entities.add(entity);
        }
    }

    public <T extends MsEntity> void removeEntity(T entity)
    {
        synchronized (syncKey)
        {
            List<T> entities = (List<T>) getEntities(entity.getClass());
            entities.remove(entity);
        }
    }
}
