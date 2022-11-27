package moysklad.entities.production;

import moysklad.entities.MsDocument;
import moysklad.entities.MsEntity;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class MsProcessingPlan implements MsEntity, Serializable
{
    private UUID id;
    private Date updated;
    private Date deleted;
    private String name;
    private String externalCode;
    private String pathName;
    private UUID ownerId;

    public UUID getId()
    {
        return id;
    }

    public void setId(UUID id)
    {
        this.id = id;
    }

    public Date getUpdated()
    {
        return updated;
    }

    public void setUpdated(Date updated)
    {
        this.updated = updated;
    }

    public Date getDeleted()
    {
        return deleted;
    }

    public void setDeleted(Date deleted)
    {
        this.deleted = deleted;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getExternalCode()
    {
        return externalCode;
    }

    public void setExternalCode(String externalCode)
    {
        this.externalCode = externalCode;
    }

    public String getPathName()
    {
        return pathName;
    }

    public void setPathName(String pathName)
    {
        this.pathName = pathName;
    }

    public UUID getOwnerId()
    {
        return ownerId;
    }

    public void setOwnerId(UUID ownerId)
    {
        this.ownerId = ownerId;
    }
}
