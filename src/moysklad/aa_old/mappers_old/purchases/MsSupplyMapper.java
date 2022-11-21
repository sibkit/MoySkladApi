package moysklad.aa_old.mappers_old.purchases;

import moysklad.core.Json;
import moysklad.aa_old.MsFormatter;
import moysklad.entities.MsEntity;
import moysklad.entities.common.MsCounterparty;
import moysklad.entities.common.MsStore;
import moysklad.entities.purchases.MsSupply;
import moysklad.aa_old.mappers_old.MsDocumentMapper;

import java.util.UUID;

public class MsSupplyMapper extends MsDocumentMapper<MsSupply>
{
    @Override
    public void bindToJson(MsSupply entity, Json jObj)
    {
        super.bindToJson(entity, jObj);
        MsFormatter f = getFormatter(jObj);

        f.setMetaId("agent", MsCounterparty.class,entity.getAgentId());
        f.setMetaId("store", MsStore.class,entity.getStoreId());
    }

    @Override
    public void bindToEntity(Json jObj, MsSupply entity)
    {
        super.bindToEntity(jObj, entity);
        MsFormatter f = getFormatter(jObj);

        entity.setAgentId(f.getMetaId("agent"));
        entity.setStoreId(f.getMetaId("store"));
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

    @Override
    public String getMsType()
    {
        return "supply";
    }

    @Override
    public UUID getEntityId(MsEntity entity)
    {
        return ((MsSupply)entity).getId();
    }
}
