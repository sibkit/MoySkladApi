package moysklad.aa_old.mappers_old.sales;

import moysklad.core.Json;
import moysklad.aa_old.MsFormatter;
import moysklad.entities.MsEntity;
import moysklad.entities.common.MsCounterparty;
import moysklad.entities.common.MsStore;
import moysklad.entities.sales.MsCustomerOrder;
import moysklad.aa_old.mappers_old.MsDocumentMapper;

import java.util.UUID;

public class MsCustomerOrderMapper extends MsDocumentMapper<MsCustomerOrder>
{
    @Override
    public void bindToJson(MsCustomerOrder entity, Json jObj)
    {
        super.bindToJson(entity, jObj);
        MsFormatter f = getFormatter(jObj);

        f.setMetaId("agent", MsCounterparty.class,entity.getAgentId());
        f.setMetaId("store", MsStore.class,entity.getStoreId());
    }

    @Override
    public void bindToEntity(Json jObj, MsCustomerOrder entity)
    {
        super.bindToEntity(jObj, entity);
        MsFormatter f = getFormatter(jObj);

        entity.setAgentId(f.getMetaId("agent"));
        entity.setStoreId(f.getMetaId("store"));
    }

    @Override
    public MsCustomerOrder createNewEntity()
    {
        return new MsCustomerOrder();
    }

    @Override
    public Class<MsCustomerOrder> getEntityClass()
    {
        return MsCustomerOrder.class;
    }

    @Override
    public String getMsType()
    {
        return "customerorder";
    }

    @Override
    public UUID getEntityId(MsEntity entity)
    {
        return ((MsCustomerOrder)entity).getId();
    }
}
