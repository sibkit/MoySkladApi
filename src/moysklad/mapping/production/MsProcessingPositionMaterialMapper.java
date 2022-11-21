package moysklad.mapping.production;

import moysklad.core.Json;
import moysklad.core.MsJsonReader;
import moysklad.entities.production.MsProcessingPositionMaterial;
import moysklad.entities.purchases.MsPurchaseOrderPosition;
import moysklad.mapping.common.MsProductPositionMapper;

public class MsProcessingPositionMaterialMapper extends MsProductPositionMapper<MsProcessingPositionMaterial>
{
    @Override
    public void bindToEntity(Json jObj, MsProcessingPositionMaterial entity)
    {
        super.bindToEntity(jObj, entity);
        entity.setProcessingId(getParentId());
    }

    @Override
    public MsProcessingPositionMaterial createNewEntity()
    {
        return new MsProcessingPositionMaterial();
    }

    @Override
    public Class<MsProcessingPositionMaterial> getEntityClass()
    {
        return MsProcessingPositionMaterial.class;
    }
}
