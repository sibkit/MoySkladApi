package moysklad.aa_old.mappers_old.common;

import moysklad.core.Json;
import moysklad.aa_old.MsFormatter;
import moysklad.entities.MsEntity;
import moysklad.entities.common.MsProduct;
import moysklad.aa_old.mappers_old.MsMapper;

import java.util.UUID;

public class MsProductMapper extends MsMapper<MsProduct>
{
    @Override
    public void bindToJson(MsProduct entity, Json jObj)
    {
        MsFormatter f = getFormatter(jObj);
        f.setUUID("id", entity.getId());
        f.setString("name", entity.getName());
        f.setString("code", entity.getCode());
        f.setString("pathName", entity.getPathName());
    }

    @Override
    public void bindToEntity(Json jObj, MsProduct entity)
    {
        MsFormatter f = getFormatter(jObj);
        entity.setId(f.getUUID("id"));
        entity.setName(f.getString("name"));
        entity.setCode(f.getString("code"));
        entity.setPathName(f.getString("pathName"));
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

    @Override
    public String getMsType()
    {
        return "product";
    }

    @Override
    public UUID getEntityId(MsEntity entity)
    {
        return ((MsProduct)entity).getId();
    }
}
