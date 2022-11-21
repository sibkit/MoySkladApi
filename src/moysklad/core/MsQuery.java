package moysklad.core;

import moysklad.entities.MsEntity;


public class MsQuery<T extends MsEntity>
{
    private final Class<T> entityClass;
    private final int limit;
    private final int offset;

    public MsQuery(Class<T> entityClass, int offset, Integer limit)
    {
        this.entityClass = entityClass;
        this.offset = offset;
        this.limit = limit;
    }

    public Class<T> getEntityClass()
    {
        return entityClass;
    }

    public int getLimit()
    {
        return limit;
    }

    public int getOffset()
    {
        return offset;
    }
}
