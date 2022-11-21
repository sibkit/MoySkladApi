package moysklad.aa_old.mappers_old.production;

import moysklad.core.Json;
import moysklad.aa_old.MsFormatter;
import moysklad.entities.MsEntity;
import moysklad.entities.production.MsProcessing;
import moysklad.entities.common.MsStore;
import moysklad.entities.production.MsProcessingPlan;
import moysklad.aa_old.mappers_old.MsDocumentMapper;

import java.util.UUID;

public class MsProcessingMapper extends MsDocumentMapper<MsProcessing>
{
    @Override
    public void bindToJson(MsProcessing entity, Json jObj)
    {
        super.bindToJson(entity, jObj);
        MsFormatter f = getFormatter(jObj);

        f.setMetaId("processingPlan", MsProcessingPlan.class, entity.getProcessingPlanId());
        f.setMetaId("materialsStore", MsStore.class, entity.getMaterialsStoreId());
        f.setMetaId("productsStore", MsStore.class, entity.getProductsStoreId());
        f.setBigDecimal("quantity",entity.getQuantity());
    }

    @Override
    public void bindToEntity(Json jObj, MsProcessing entity)
    {
        super.bindToEntity(jObj, entity);
        MsFormatter f = getFormatter(jObj);

        entity.setProcessingPlanId(f.getMetaId("processingPlan"));
        entity.setMaterialsStoreId(f.getMetaId("materialsStore"));
        entity.setProductsStoreId(f.getMetaId("productsStore"));
        entity.setQuantity(f.getBigDecimal("quantity"));
    }

    @Override
    public MsProcessing createNewEntity()
    {
        return new MsProcessing();
    }

    @Override
    public Class<MsProcessing> getEntityClass()
    {
        return MsProcessing.class;
    }

    @Override
    public String getMsType()
    {
        return "processing";
    }

    @Override
    public UUID getEntityId(MsEntity entity)
    {
        return ((MsProcessing)entity).getId();
    }
}
