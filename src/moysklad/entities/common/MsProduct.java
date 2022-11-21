package moysklad.entities.common;

import moysklad.entities.MsEntity;

import java.util.UUID;

public class MsProduct implements MsEntity
{
    private UUID id;
    private String code;
    private String name;
    private String pathName;

    public UUID getId()
    {
        return id;
    }

    public void setId(UUID id)
    {
        this.id = id;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getPathName()
    {
        return pathName;
    }

    public void setPathName(String pathName)
    {
        this.pathName = pathName;
    }
}
