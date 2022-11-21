package moysklad.mapping.production;

import moysklad.core.Json;
import moysklad.entities.production.MsProcessingPositionMaterial;
import moysklad.entities.production.MsProcessingPositionResult;
import moysklad.mapping.common.MsProductPositionMapper;

public class MsProcessingPositionResultMapper extends MsProductPositionMapper<MsProcessingPositionResult>
{
    @Override
    public void bindToEntity(Json jObj, MsProcessingPositionResult entity)
    {
        super.bindToEntity(jObj, entity);
        entity.setProcessingId(getParentId());
    }

    @Override
    public MsProcessingPositionResult createNewEntity()
    {
        return new MsProcessingPositionResult();
    }

    @Override
    public Class<MsProcessingPositionResult> getEntityClass()
    {
        return MsProcessingPositionResult.class;
    }
}
