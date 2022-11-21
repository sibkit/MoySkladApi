package moysklad.mapping;

import moysklad.entities.MsEntity;

import java.util.UUID;

public abstract class MsChildMapperBase<T extends MsEntity> extends MsEntityMapperBase<T> implements MsChildMapper
{
    private UUID parentId;


    public UUID getParentId()
    {
        return parentId;
    }

    public void setParentId(UUID parentId)
    {
        this.parentId = parentId;
    }
}
