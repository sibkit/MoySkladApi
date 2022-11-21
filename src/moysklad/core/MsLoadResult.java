package moysklad.core;

public class MsLoadResult
{
    private Class entityClass;
    private int loadedOffset;
    private int loadedSize;
    private int totalSize;

    public MsLoadResult(Class entityClass, int loadedOffset, int loadedSize, int totalSize)
    {
        this.entityClass = entityClass;
        this.loadedOffset = loadedOffset;
        this.loadedSize = loadedSize;
        this.totalSize = totalSize;
    }

    public int getLoadedSize()
    {
        return loadedSize;
    }

    public int getTotalSize()
    {
        return totalSize;
    }

    public int getLoadedOffset()
    {
        return loadedOffset;
    }

    public Class getEntityClass()
    {
        return entityClass;
    }
}
