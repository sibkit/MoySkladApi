package moysklad.mapping.audit;

import moysklad.core.Json;
import moysklad.core.MsJsonReader;
import moysklad.entities.attributes.MsAttribute;
import moysklad.mapping.MsEntityMapperBase;

public class MsAttributeMapper extends MsEntityMapperBase<MsAttribute>
{
    @Override
    public void bindToEntity(Json jObj, MsAttribute entity)
    {
        MsJsonReader r = getJsonReader(jObj);
        entity.setId(r.readUUID("id"));

        entity.setName(r.readString("name"));
        entity.setType(r.readString("type"));
        entity.setEntityType(jObj.at("meta").at("type").asString());
    }

    @Override
    public MsAttribute createNewEntity()
    {
        return new MsAttribute();
    }

    @Override
    public Class<MsAttribute> getEntityClass()
    {
        return MsAttribute.class;
    }
}
