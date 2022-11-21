package moysklad.aa_old.mappers_old.purchases;

import moysklad.core.Json;
import moysklad.aa_old.MsFormatter;
import moysklad.entities.MsEntity;
import moysklad.entities.common.MsCounterparty;
import moysklad.entities.common.MsStore;
import moysklad.entities.purchases.MsPurchaseOrder;
import moysklad.aa_old.mappers_old.MsDocumentMapper;

import java.util.UUID;

public class MsPurchaseOrderMapper extends MsDocumentMapper<MsPurchaseOrder>
{
    @Override
    public void bindToJson(MsPurchaseOrder entity, Json jObj)
    {
        super.bindToJson(entity, jObj);
        MsFormatter f = getFormatter(jObj);

        f.setMetaId("agent", MsCounterparty.class,entity.getAgentId());
        f.setMetaId("store", MsStore.class,entity.getStoreId());
    }

    @Override
    public void bindToEntity(Json jObj, MsPurchaseOrder entity)
    {
        super.bindToEntity(jObj, entity);
        MsFormatter f = getFormatter(jObj);

        entity.setAgentId(f.getMetaId("agent"));
        entity.setStoreId(f.getMetaId("store"));
    }

    @Override
    public MsPurchaseOrder createNewEntity()
    {
        return new MsPurchaseOrder();
    }

    @Override
    public Class<MsPurchaseOrder> getEntityClass()
    {
        return MsPurchaseOrder.class;
    }

    @Override
    public String getMsType()
    {
        return "purchaseorder";
    }

    @Override
    public UUID getEntityId(MsEntity entity)
    {
        return ((MsPurchaseOrder)entity).getId();
    }

}
