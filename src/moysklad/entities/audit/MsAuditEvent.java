package moysklad.entities.audit;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class MsAuditEvent implements Serializable {
    private String uid;
    private Boolean supportAccess;
    private String source;
    private Date moment;
    private Integer objectsCount;
    private String eventType;
    private String entityType;
    private String objectType;
    private String name;
    private UUID entityId;
    private String additionalInfo;
    private UUID auditId;
    private String diff;

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

    public Integer getObjectsCount()
    {
        return objectsCount;
    }

    public void setObjectsCount(Integer objectsCount)
    {
        this.objectsCount = objectsCount;
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

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public UUID getEntityId()
    {
        return entityId;
    }

    public void setEntityId(UUID entityId)
    {
        this.entityId = entityId;
    }

    public String getAdditionalInfo()
    {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo)
    {
        this.additionalInfo = additionalInfo;
    }

    public UUID getAuditId()
    {
        return auditId;
    }

    public void setAuditId(UUID auditId)
    {
        this.auditId = auditId;
    }

    public String getDiff()
    {
        return diff;
    }

    public void setDiff(String diff)
    {
        this.diff = diff;
    }
}
