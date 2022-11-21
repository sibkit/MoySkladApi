package moysklad.aa_old.mappers_old;

import moysklad.aa_old.EntityNode_old;
import moysklad.configuration.MsConfiguration;
import moysklad.core.Json;
import moysklad.aa_old.MsFormatter;
import moysklad.entities.MsEntity;

import java.util.List;
import java.util.UUID;


public abstract class MsMapper<T extends MsEntity>
{
    private UUID parentId;
    private EntityNode_old node;

    public void bindToJson(T entity, Json jObj) { }

    public abstract void bindToEntity(Json jObj, T entity);
    public abstract T createNewEntity();
    public abstract Class<T> getEntityClass();

    public abstract String getMsType();

    public List<Json> getJsonEntities(Json responseObject)
    {
        return responseObject.at("rows").asJsonList();
    }

    public Integer getMaxLimit()
    {
        return 1000;
    }

    public String getServerLoadPath()
    {
        return null;
        //if(getParentId() ==null)
        //    return "/entity/"+getMsType();
        //else
        //    return "/entity/"+getNode().getParentNode().getMapper().getMsType()+"/"+getParentId()+"/"+getNode().getNameInParent();
    }

    public abstract UUID getEntityId(MsEntity entity);

    private MsConfiguration configuration;

    public void setConfiguration(MsConfiguration configuration)
    {
        this.configuration = configuration;
    }

    public MsFormatter getFormatter(Json jObj)
    {
        MsFormatter result = new MsFormatter(jObj, getConfiguration());
        return result;
    }

    public MsConfiguration getConfiguration()
    {
        return configuration;
    }

    public UUID getParentId()
    {
        return parentId;
    }

    public EntityNode_old getNode()
    {
        return node;
    }
    public void setNode(EntityNode_old node)
    {
        this.node = node;
    }

    public void setParentId(UUID parentId)
    {
        this.parentId = parentId;
    }
}
