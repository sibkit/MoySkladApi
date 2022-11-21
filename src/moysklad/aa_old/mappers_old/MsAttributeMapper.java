package moysklad.aa_old.mappers_old;

import moysklad.core.Json;
import moysklad.entities.MsEntity;
import moysklad.entities.attributes.MsAttribute;

import java.util.UUID;

public class MsAttributeMapper extends MsMapper<MsAttribute>
{
   @Override
    public void bindToEntity(Json jObj, MsAttribute entity)
    {

    }

    @Override
    public MsAttribute createNewEntity()
    {
        return null;
    }

    @Override
    public Class<MsAttribute> getEntityClass()
    {
        return null;
    }

    @Override
    public String getMsType()
    {
        return null;
    }

    @Override
    public UUID getEntityId(MsEntity entity)
    {
        return null;
    }
}
