package moysklad.entities.attributes;

import java.util.UUID;

public class MsAttributeValue
{
    private UUID attributeId;
    private UUID entityId;
    private String value;

    public UUID getAttributeId()
    {
        return attributeId;
    }

    public void setAttributeId(UUID attributeId)
    {
        this.attributeId = attributeId;
    }

    public UUID getEntityId()
    {
        return entityId;
    }

    public void setEntityId(UUID entityId)
    {
        this.entityId = entityId;
    }

    public String getValue()
    {
        return value;
    }

    public void setValue(String value)
    {
        this.value = value;
    }
}
