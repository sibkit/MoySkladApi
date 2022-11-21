package moysklad.aa_old.mappers_old.production;

import moysklad.core.Json;
import moysklad.aa_old.MsFormatter;
import moysklad.entities.MsEntity;
import moysklad.entities.common.MsEmployee;
import moysklad.entities.production.MsProcessingPlan;
import moysklad.aa_old.mappers_old.MsMapper;

import java.util.UUID;

public class MsProcessingPlanMapper extends MsMapper<MsProcessingPlan>
{
    @Override
    public void bindToJson(MsProcessingPlan entity, Json jObj)
    {
        MsFormatter f = getFormatter(jObj);
        f.setUUID("id", entity.getId());
        f.setDate("updated", entity.getUpdated());
        f.setDate("deleted", entity.getDeleted());
        f.setString("name", entity.getName());
        f.setString("externalCode", entity.getExternalCode());
        f.setString("pathName", entity.getPathName());
        f.setMetaId("owner", MsEmployee.class,entity.getOwnerId());

    }

    @Override
    public void bindToEntity(Json jObj, MsProcessingPlan entity)
    {
        /*
            private UUID id;
            private Date updated;
            private Date deleted;
            private String name;
            private String externalCode;
            private String pathName;
            private UUID ownerId;
         */
        MsFormatter f = getFormatter(jObj);
        entity.setId(f.getUUID("id"));
        entity.setUpdated(f.getDate("updated"));
        entity.setDeleted(f.getDate("deleted"));
        entity.setName(f.getString("name"));
        entity.setExternalCode(f.getString("externalCode"));
        entity.setPathName(f.getString("pathName"));
        entity.setOwnerId(f.getMetaId("owner"));
    }

    @Override
    public MsProcessingPlan createNewEntity()
    {
        return new MsProcessingPlan();
    }

    @Override
    public Class<MsProcessingPlan> getEntityClass()
    {
        return MsProcessingPlan.class;
    }

    @Override
    public String getMsType()
    {
        return "processingplan";
    }

    @Override
    public UUID getEntityId(MsEntity entity)
    {
        return ((MsProcessingPlan)entity).getId();
    }
}
