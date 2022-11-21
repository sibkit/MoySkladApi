package moysklad.aa_old.mappers_old.common;

import moysklad.core.Json;
import moysklad.entities.MsEntity;
import moysklad.entities.common.MsCounterparty;
import moysklad.aa_old.mappers_old.MsMapper;

import java.util.UUID;

public class MsCounterpartyMapper extends MsMapper<MsCounterparty>
{
    @Override
    public void bindToJson(MsCounterparty entity, Json jObj)
    {
        throw new RuntimeException("NOT IMPLEMENTED");
    }

    @Override
    public void bindToEntity(Json jObj, MsCounterparty entity)
    {
        throw new RuntimeException("NOT IMPLEMENTED");
    }

    @Override
    public MsCounterparty createNewEntity()
    {
        return new MsCounterparty();
    }

    @Override
    public Class<MsCounterparty> getEntityClass()
    {
        return MsCounterparty.class;
    }

    @Override
    public String getMsType()
    {
        return "counterparty";
    }

    @Override
    public UUID getEntityId(MsEntity entity)
    {
        return ((MsCounterparty)entity).getId();
    }
}
