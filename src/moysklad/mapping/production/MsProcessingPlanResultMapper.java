package moysklad.mapping.production;

import moysklad.core.Json;
import moysklad.entities.production.MsProcessingPlanResult;
import moysklad.mapping.common.MsProductPositionMapper;

public class MsProcessingPlanResultMapper extends MsProductPositionMapper<MsProcessingPlanResult>
{
    @Override
    public void bindToEntity(Json jObj, MsProcessingPlanResult entity)
    {
        super.bindToEntity(jObj, entity);
        entity.setProcessingPlanId(getParentId());
    }

    @Override
    public MsProcessingPlanResult createNewEntity()
    {
        return new MsProcessingPlanResult();
    }

    @Override
    public Class<MsProcessingPlanResult> getEntityClass()
    {
        return MsProcessingPlanResult.class;
    }
}