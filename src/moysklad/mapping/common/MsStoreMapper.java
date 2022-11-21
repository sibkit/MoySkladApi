package moysklad.mapping.common;


import moysklad.core.Json;
import moysklad.core.MsJsonReader;
import moysklad.entities.common.MsStore;
import moysklad.mapping.MsEntityMapperBase;

public class MsStoreMapper  extends MsEntityMapperBase<MsStore>
{
    @Override
    public void bindToEntity(Json jObj, MsStore entity)
    {
        MsJsonReader r = getJsonReader(jObj);
        entity.setId(r.readUUID("id"));
        entity.setCode(r.readString("code"));
        entity.setName(r.readString("name"));
        entity.setDescription(r.readString("description"));
    }

    @Override
    public MsStore createNewEntity() {
        return new MsStore();
    }

    @Override
    public Class<MsStore> getEntityClass() {
        return MsStore.class;
    }
}
