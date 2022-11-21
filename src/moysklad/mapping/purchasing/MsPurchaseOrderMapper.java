package moysklad.mapping.purchasing;

import moysklad.core.Json;
import moysklad.core.MsJsonReader;
import moysklad.entities.purchases.MsPurchaseOrder;
import moysklad.mapping.MsDocumentMapper;
import moysklad.mapping.MsEntityMapperBase;

public class MsPurchaseOrderMapper extends MsDocumentMapper<MsPurchaseOrder>
{
    @Override
    public void bindToEntity(Json jObj, MsPurchaseOrder entity)
    {
        super.bindToEntity(jObj, entity);

        MsJsonReader jReader = getJsonReader(jObj);

        entity.setAgentId(jReader.readMetaId("agent", true));
        entity.setStoreId(jReader.readMetaId("store", false));
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

}
