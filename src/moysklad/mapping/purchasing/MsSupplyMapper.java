package moysklad.mapping.purchasing;

import moysklad.core.Json;
import moysklad.core.MsJsonReader;
import moysklad.entities.purchases.MsPurchaseOrder;
import moysklad.entities.purchases.MsSupply;
import moysklad.mapping.MsDocumentMapper;

public class MsSupplyMapper extends MsDocumentMapper<MsSupply>
{
    @Override
    public void bindToEntity(Json jObj, MsSupply entity)
    {
        super.bindToEntity(jObj, entity);

        MsJsonReader jReader = getJsonReader(jObj);

        entity.setAgentId(jReader.readMetaId("agent", true));
        entity.setStoreId(jReader.readMetaId("store", true));
    }

    @Override
    public MsSupply createNewEntity()
    {
        return new MsSupply();
    }

    @Override
    public Class<MsSupply> getEntityClass()
    {
        return MsSupply.class;
    }
}
