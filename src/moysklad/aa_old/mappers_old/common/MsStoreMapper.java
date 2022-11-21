package moysklad.aa_old.mappers_old.common;

import moysklad.core.Json;
import moysklad.aa_old.MsFormatter;
import moysklad.entities.MsEntity;
import moysklad.entities.common.MsStore;
import moysklad.aa_old.mappers_old.MsMapper;

import java.util.UUID;

public class MsStoreMapper extends MsMapper<MsStore>
{
    @Override
    public void bindToJson(MsStore entity, Json jObj)
    {
        MsFormatter f = getFormatter(jObj);
        f.setUUID("id",entity.getId());
        f.setString("name", entity.getName());
        f.setString("code", entity.getCode());
        f.setString("description",entity.getDescription());
    }

    @Override
    public void bindToEntity(Json jObj, MsStore entity)
    {
        MsFormatter f = getFormatter(jObj);
        entity.setId(f.getUUID("id"));
        entity.setCode(f.getString("code"));
        entity.setName(f.getString("name"));
        entity.setDescription(f.getString("description"));
    }

    @Override
    public MsStore createNewEntity()
    {
        return new MsStore();
    }

    @Override
    public Class<MsStore> getEntityClass()
    {
        return MsStore.class;
    }

    @Override
    public String getMsType()
    {
        return "store";
    }

    @Override
    public UUID getEntityId(MsEntity entity)
    {
        return ((MsStore)entity).getId();
    }
}
