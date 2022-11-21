package moysklad.aa_old.mappers_old.audit;

import moysklad.core.Json;
import moysklad.aa_old.MsFormatter;
import moysklad.entities.MsEntity;
import moysklad.entities.audit.MsAudit;
import moysklad.aa_old.mappers_old.MsMapper;

import java.util.UUID;

public class MsAuditMapper extends MsMapper<MsAudit>
{
    @Override
    public void bindToJson(MsAudit entity, Json jObj)
    {
        /*
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
         */

        MsFormatter f = getFormatter(jObj);
        f.setUUID("id", entity.getId());
        f.setString("uid",entity.getUid());
        f.setBoolean("supportAccess",entity.getSupportAccess());
        f.setString("source",entity.getSource());
        f.setDate("moment", entity.getMoment());
        f.setInteger("objectsCount",entity.getObjectCount());
        f.setString("eventType", entity.getEventType());
        f.setString("entityType", entity.getEntityType());
        f.setString("objectType", entity.getObjectType());
        f.setString("info", entity.getInfo());
    }

    @Override
    public void bindToEntity(Json jObj, MsAudit entity)
    {
        MsFormatter f = getFormatter(jObj);

        entity.setId(f.getUUID("id"));
        entity.setUid(f.getString("uid"));
        entity.setSupportAccess(f.getBoolean("supportAccess"));
        entity.setSource(f.getString("source"));
        entity.setMoment(f.getDate("moment"));
        entity.setObjectCount(f.getInteger("objectsCount"));
        entity.setEventType(f.getString("eventType"));
        entity.setEntityType(f.getString("entityType"));
        entity.setObjectType(f.getString("objectType"));
        entity.setInfo(f.getString("info"));
    }

    @Override
    public Integer getMaxLimit()
    {
        return 25;
    }

    @Override
    public String getServerLoadPath()
    {
        return "/"+getMsType();
    }

    @Override
    public MsAudit createNewEntity()
    {
        return new MsAudit();
    }

    @Override
    public Class<MsAudit> getEntityClass()
    {
        return MsAudit.class;
    }

    @Override
    public String getMsType()
    {
        return "audit";
    }

    @Override
    public UUID getEntityId(MsEntity entity)
    {
        return ((MsAudit)entity).getId();
    }
}
