package moysklad.mapping.production;

import moysklad.core.Json;
import moysklad.core.MsJsonReader;
import moysklad.entities.production.MsProcessing;
import moysklad.entities.purchases.MsPurchaseOrder;
import moysklad.mapping.MsDocumentMapper;

public class MsProcessingMapper extends MsDocumentMapper<MsProcessing>
{
    @Override
    public void bindToEntity(Json jObj, MsProcessing entity)
    {
        super.bindToEntity(jObj, entity);

        MsJsonReader r = getJsonReader(jObj);

        entity.setProcessingPlanId(r.readMetaId("processingPlan", true));
        entity.setMaterialsStoreId(r.readMetaId("materialsStore", true));
        entity.setProductsStoreId(r.readMetaId("productsStore", true));
        entity.setQuantity(r.readBigDecimal("quantity"));

        //entity.setAgentId(jReader.readMetaId("agent"));
        //entity.setStoreId(jReader.readMetaId("store"));
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
}
