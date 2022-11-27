package moysklad.entities.common;

import moysklad.entities.MsEntity;

import java.io.Serializable;
import java.util.UUID;

public class MsStore implements MsEntity, Serializable
{
    private UUID id;
    private String name;
    private String code;
    private String description;

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

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }
}
