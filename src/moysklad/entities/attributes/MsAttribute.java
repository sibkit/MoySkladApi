package moysklad.entities.attributes;

import moysklad.entities.MsEntity;

import java.io.Serializable;
import java.util.UUID;

public class MsAttribute implements MsEntity, Serializable
{
    private UUID id;
    private String name;
    private String entityType;
    private String type;

    public UUID getId()
    {
        return id;
    }

    public void setId(UUID id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getEntityType()
    {
        return entityType;
    }

    public void setEntityType(String entityType)
    {
        this.entityType = entityType;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }
}
