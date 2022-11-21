package moysklad.aa_old.mappers_old.common;

import moysklad.core.Json;
import moysklad.aa_old.MsFormatter;
import moysklad.entities.MsEntity;
import moysklad.entities.common.MsOrganization;
import moysklad.aa_old.mappers_old.MsMapper;

import java.util.UUID;

public class MsOrganizationMapper extends MsMapper<MsOrganization>
{
    @Override
    public void bindToJson(MsOrganization entity, Json jObj)
    {
        MsFormatter f = getFormatter(jObj);
        f.setUUID("id",entity.getId());
        f.setString("name",entity.getName());
    }

    @Override
    public void bindToEntity(Json jObj, MsOrganization entity)
    {
        MsFormatter f = getFormatter(jObj);
        entity.setId(f.getUUID("id"));
        entity.setName(f.getString("name"));
    }

    @Override
    public MsOrganization createNewEntity()
    {
        return new MsOrganization();
    }

    @Override
    public Class<MsOrganization> getEntityClass()
    {
        return MsOrganization.class;
    }

    @Override
    public String getMsType()
    {
        return "organization";
    }

    @Override
    public UUID getEntityId(MsEntity entity)
    {
        return ((MsOrganization)entity).getId();
    }
}
