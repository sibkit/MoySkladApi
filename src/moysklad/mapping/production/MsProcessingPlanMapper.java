package moysklad.mapping.production;

import moysklad.core.Json;
import moysklad.core.MsJsonReader;
import moysklad.entities.production.MsProcessingPlan;
import moysklad.mapping.MsEntityMapperBase;

public class MsProcessingPlanMapper extends MsEntityMapperBase<MsProcessingPlan>
{
    @Override
    public void bindToEntity(Json jObj, MsProcessingPlan entity)
    {
        MsJsonReader r = getJsonReader(jObj);
        entity.setId(r.readUUID("id"));
        entity.setName(r.readString("name"));
        entity.setExternalCode(r.readString("externalCode"));
        entity.setPathName(r.readString("pathName"));
        entity.setUpdated(r.readDate("updated"));
        entity.setDeleted(r.readDate("deleted"));
        entity.setOwnerId(r.readMetaId("owner",true));
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
}
