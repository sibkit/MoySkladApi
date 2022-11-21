package moysklad.entities.common;

import moysklad.entities.MsEntity;

import java.util.UUID;

public class MsCounterparty implements MsEntity
{
    private UUID id;
    private String name;

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
}
