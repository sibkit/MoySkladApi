package moysklad.entities;

import moysklad.entities.MsEntity;

import java.util.Date;
import java.util.UUID;

public abstract class MsDocument implements MsEntity
{
    private UUID id;
    private UUID syncId;
    private Date updated;
    private Date deleted;
    private String name;
    private String description;
    private String externalCode;
    private Date moment;
    private Boolean applicable;
    private UUID ownerId;

    private UUID organizationId;
    private UUID stateId;

    private Date created;

    public UUID getId()
    {
        return id;
    }

    public void setId(UUID id)
    {
        this.id = id;
    }

    public UUID getSyncId()
    {
        return syncId;
    }

    public void setSyncId(UUID syncId)
    {
        this.syncId = syncId;
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

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getExternalCode()
    {
        return externalCode;
    }

    public void setExternalCode(String externalCode)
    {
        this.externalCode = externalCode;
    }

    public Date getMoment()
    {
        return moment;
    }

    public void setMoment(Date moment)
    {
        this.moment = moment;
    }

    public Boolean getApplicable()
    {
        return applicable;
    }

    public void setApplicable(Boolean applicable)
    {
        this.applicable = applicable;
    }

    public UUID getOwnerId()
    {
        return ownerId;
    }

    public void setOwnerId(UUID ownerId)
    {
        this.ownerId = ownerId;
    }

    public UUID getOrganizationId()
    {
        return organizationId;
    }

    public void setOrganizationId(UUID organizationId)
    {
        this.organizationId = organizationId;
    }

    public UUID getStateId()
    {
        return stateId;
    }

    public void setStateId(UUID stateId)
    {
        this.stateId = stateId;
    }

    public Date getCreated()
    {
        return created;
    }

    public void setCreated(Date created)
    {
        this.created = created;
    }
}
