package moysklad.mapping.common;


import moysklad.core.Json;
import moysklad.core.MsJsonReader;
import moysklad.entities.common.MsProductPosition;
import moysklad.mapping.MsChildMapperBase;

public abstract class MsProductPositionMapper<T extends MsProductPosition> extends MsChildMapperBase<T>
{
    @Override
    public void bindToEntity(Json jObj, T entity)
    {
        MsJsonReader jReader = getJsonReader(jObj);
        entity.setId(jReader.readUUID("id"));
        entity.setProductId(jReader.readMetaId("assortment", true));
        entity.setQuantity(jReader.readBigDecimal("quantity"));
    }
}
