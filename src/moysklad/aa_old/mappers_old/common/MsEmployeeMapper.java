package moysklad.aa_old.mappers_old.common;

import moysklad.core.Json;
import moysklad.aa_old.MsFormatter;
import moysklad.entities.MsEntity;
import moysklad.entities.common.MsEmployee;
import moysklad.aa_old.mappers_old.MsMapper;

import java.util.UUID;

public class MsEmployeeMapper extends MsMapper<MsEmployee>
{
    @Override
    public void bindToJson(MsEmployee entity, Json jObj)
    {
        MsFormatter f = getFormatter(jObj);
        f.setUUID("id", entity.getId());
        f.setString("name", entity.getName());
        f.setString("description", entity.getName());
        f.setString("uid", entity.getName());
        f.setString("firstName", entity.getName());
        f.setString("lastName", entity.getName());
        f.setString("middleName", entity.getName());
    }

    @Override
    public void bindToEntity(Json jObj, MsEmployee entity)
    {
        MsFormatter f = getFormatter(jObj);
        entity.setId(f.getUUID("id"));
        entity.setName(f.getString("name"));
        entity.setDescription(f.getString("description"));
        entity.setUid(f.getString("uid"));
        entity.setFirstName(f.getString("firstName"));
        entity.setLastName(f.getString("lastName"));
        entity.setMiddleName(f.getString("middleName"));
    }

    @Override
    public MsEmployee createNewEntity()
    {
        return new MsEmployee();
    }

    @Override
    public Class<MsEmployee> getEntityClass()
    {
        return MsEmployee.class;
    }

    @Override
    public String getMsType()
    {
        return "employee";
    }

    @Override
    public UUID getEntityId(MsEntity entity)
    {
        return ((MsEmployee)entity).getId();
    }
}
