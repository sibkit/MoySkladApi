package moysklad.aa_old.mappers_old.purchases;

import moysklad.core.Json;
import moysklad.entities.MsEntity;
import moysklad.entities.purchases.MsPurchaseOrderPosition;
import moysklad.aa_old.mappers_old.MsMapper;

import java.util.UUID;

public class MsPurchaseOrderPositionMapper extends MsMapper<MsPurchaseOrderPosition>
{
    @Override
    public void bindToJson(MsPurchaseOrderPosition entity, Json jObj)
    {

    }

    @Override
    public void bindToEntity(Json jObj, MsPurchaseOrderPosition entity)
    {

    }

    @Override
    public MsPurchaseOrderPosition createNewEntity()
    {
        return new MsPurchaseOrderPosition();
    }

    @Override
    public Class<MsPurchaseOrderPosition> getEntityClass()
    {
        return MsPurchaseOrderPosition.class;
    }

    @Override
    public String getMsType()
    {
        return "purchaseorderposition";
    }

    @Override
    public UUID getEntityId(MsEntity entity)
    {
        return ((MsPurchaseOrderPosition)entity).getId();
    }
}
