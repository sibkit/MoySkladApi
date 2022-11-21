package moysklad.mapping;

import moysklad.configuration.nodes.EntityNode;
import moysklad.core.Json;
import moysklad.core.MsJsonReader;

public abstract class MsEntityMapperBase<T>
{
    private EntityNode node;
    private MsJsonReader jReader;

    public abstract void bindToEntity(Json jObj, T entity);
    public abstract T createNewEntity();

    public abstract Class<T> getEntityClass();

    public EntityNode getNode()
    {
        return this.node;
    }
    public void setNode(EntityNode node)
    {
        this.node = node;
    }

    protected MsJsonReader getJsonReader(Json jObj)
    {
        if(jReader==null)
            jReader = new MsJsonReader();
        jReader.setJsonObject(jObj);
        return jReader;
    }
}
