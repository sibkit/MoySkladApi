package moysklad.mapping.products;

import moysklad.core.Json;
import moysklad.core.MsJsonReader;
import moysklad.entities.products.MsMove;
import moysklad.entities.purchases.MsPurchaseOrder;
import moysklad.mapping.MsDocumentMapper;

public class MsMoveMapper extends MsDocumentMapper<MsMove> {

    @Override
    public void bindToEntity(Json jObj, MsMove entity)
    {
        super.bindToEntity(jObj, entity);

        MsJsonReader jReader = getJsonReader(jObj);
        entity.setSourceStoreId(jReader.readMetaId("sourceStore",true));
        entity.setTargetStoreId(jReader.readMetaId("targetStore", true));
    }

    @Override
    public MsMove createNewEntity() {
        return new MsMove();
    }

    @Override
    public Class<MsMove> getEntityClass() {
        return MsMove.class;
    }
}
