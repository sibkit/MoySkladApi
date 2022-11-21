package moysklad.aa_old.mappers_old.production;

import moysklad.core.Json;
import moysklad.aa_old.MsFormatter;
import moysklad.entities.MsEntity;
import moysklad.entities.common.MsStore;
import moysklad.entities.production.MsProcessingOrder;
import moysklad.entities.production.MsProcessingPlan;
import moysklad.aa_old.mappers_old.MsDocumentMapper;

import java.util.UUID;

public class MsProcessingOrderMapper extends MsDocumentMapper<MsProcessingOrder>
{

    @Override
    public void bindToJson(MsProcessingOrder entity, Json jObj)
    {
        super.bindToJson(entity, jObj);
        MsFormatter f = getFormatter(jObj);
        f.setMetaId(MsProcessingOrder.class, entity.getId());
        f.setMetaId("processingPlan", MsProcessingPlan.class, entity.getProcessingPlanId());

        f.setMetaId("store", MsStore.class,entity.getStoreId());
        f.setBigDecimal("quantity",entity.getQuantity());
    }

    @Override
    public void bindToEntity(Json jObj, MsProcessingOrder entity)
    {
        super.bindToEntity(jObj, entity);
        MsFormatter f = getFormatter(jObj);

        entity.setProcessingPlanId(f.getMetaId("processingPlan"));
        entity.setStoreId(f.getMetaId("store"));
        entity.setQuantity(f.getBigDecimal("quantity"));
    }

    @Override
    public MsProcessingOrder createNewEntity()
    {
        return new MsProcessingOrder();
    }

    @Override
    public Class<MsProcessingOrder> getEntityClass()
    {
        return MsProcessingOrder.class;
    }

    @Override
    public String getMsType()
    {
        return "processingorder";
    }

    @Override
    public UUID getEntityId(MsEntity entity)
    {
        return ((MsProcessingOrder)entity).getId();
    }
}
