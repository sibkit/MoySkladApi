package moysklad.mapping.common;

import moysklad.core.Json;
import moysklad.core.MsJsonReader;
import moysklad.entities.common.MsProduct;
import moysklad.mapping.MsEntityMapperBase;

public class MsProductMapper extends MsEntityMapperBase<MsProduct>
{
    @Override
    public void bindToEntity(Json jObj, MsProduct entity)
    {
        MsJsonReader r = getJsonReader(jObj);
        entity.setId(r.readUUID("id"));
        entity.setCode(r.readString("code"));
        entity.setName(r.readString("name"));
        entity.setPathName(r.readString("pathName"));
    }

    @Override
    public MsProduct createNewEntity()
    {
        return new MsProduct();
    }

    @Override
    public Class<MsProduct> getEntityClass()
    {
        return MsProduct.class;
    }
}
