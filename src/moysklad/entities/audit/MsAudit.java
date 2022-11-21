package moysklad.entities.audit;

import moysklad.entities.MsEntity;

import java.util.Date;
import java.util.UUID;

public class MsAudit implements MsEntity
{
    private UUID id;
    private String uid;
    private Boolean supportAccess;
    private String source;
    private Date moment;
    private Integer objectCount;
    private String eventType;
    private String entityType;
    private String objectType;
    private String info;

    public UUID getId()
    {
        return id;
    }

    public void setId(UUID id)
    {
        this.id = id;
    }

    public String getUid()
    {
        return uid;
    }

    public void setUid(String uid)
    {
        this.uid = uid;
    }

    public Boolean getSupportAccess()
    {
        return supportAccess;
    }

    public void setSupportAccess(Boolean supportAccess)
    {
        this.supportAccess = supportAccess;
    }

    public String getSource()
    {
        return source;
    }

    public void setSource(String source)
    {
        this.source = source;
    }

    public Date getMoment()
    {
        return moment;
    }

    public void setMoment(Date moment)
    {
        this.moment = moment;
    }

    public Integer getObjectCount()
    {
        return objectCount;
    }

    public void setObjectCount(Integer objectCount)
    {
        this.objectCount = objectCount;
    }

    public String getEventType()
    {
        return eventType;
    }

    public void setEventType(String eventType)
    {
        this.eventType = eventType;
    }

    public String getEntityType()
    {
        return entityType;
    }

    public void setEntityType(String entityType)
    {
        this.entityType = entityType;
    }

    public String getObjectType()
    {
        return objectType;
    }

    public void setObjectType(String objectType)
    {
        this.objectType = objectType;
    }

    public String getInfo()
    {
        return info;
    }

    public void setInfo(String info)
    {
        this.info = info;
    }
}
