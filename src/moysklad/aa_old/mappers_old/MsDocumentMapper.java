package moysklad.aa_old.mappers_old;

import moysklad.core.Json;
import moysklad.aa_old.MsFormatter;
import moysklad.entities.MsDocument;
import moysklad.entities.common.MsEmployee;
import moysklad.entities.common.MsOrganization;

public abstract class MsDocumentMapper<T extends MsDocument> extends MsMapper<T>
{

    @Override
    public void bindToJson(T entity, Json jObj)
    {
        MsFormatter f = getFormatter(jObj);
        f.setBoolean("applicable", entity.getApplicable());
        //f.setDate("created",entity.getCreated());
        //f.setDate("deleted",entity.getDeleted());
        f.setString("description", entity.getDescription());
        f.setString("externalCode",entity.getExternalCode());
        f.setUUID("id",entity.getId());
        f.setDate("moment",entity.getMoment());
        f.setString("name",entity.getName());
        f.setMetaId("organization", MsOrganization.class,entity.getOrganizationId());
        f.setMetaId("owner", MsEmployee.class,entity.getOwnerId());
        //f.setDate("updated", entity.getUpdated());
    }

    @Override
    public void bindToEntity(Json jObj, T e)
    {
        MsFormatter f = getFormatter(jObj);
        e.setApplicable(f.getBoolean("applicable"));
        e.setCreated(f.getDate("created"));
        e.setDeleted(f.getDate("deleted"));
        e.setDescription(f.getString("description"));
        e.setExternalCode(f.getString("externalCode"));

        e.setId(f.getUUID("id"));
        e.setMoment(f.getDate("moment"));
        e.setName(f.getString("name"));
        e.setOrganizationId(f.getMetaId("organization"));
        e.setOwnerId(f.getMetaId("owner"));
        e.setUpdated(f.getDate("updated"));
    }

}
