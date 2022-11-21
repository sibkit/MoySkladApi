package moysklad.mapping.production;

import moysklad.core.Json;
import moysklad.entities.production.MsProcessingPlanMaterial;
import moysklad.mapping.common.MsProductPositionMapper;

public class MsProcessingPlanMaterialMapper extends MsProductPositionMapper<MsProcessingPlanMaterial>
{
    @Override
    public void bindToEntity(Json jObj, MsProcessingPlanMaterial entity)
    {
        super.bindToEntity(jObj, entity);
        entity.setProcessingPlanId(getParentId());
    }

    @Override
    public MsProcessingPlanMaterial createNewEntity()
    {
        return new MsProcessingPlanMaterial();
    }

    @Override
    public Class<MsProcessingPlanMaterial> getEntityClass()
    {
        return MsProcessingPlanMaterial.class;
    }
}
