package moysklad.mapping;

import moysklad.core.Json;
import moysklad.core.MsJsonReader;
import moysklad.entities.MsDocument;
import moysklad.entities.MsEntity;

public abstract class MsDocumentMapper<T extends MsDocument> extends MsEntityMapperBase<T>
{
    @Override
    public void bindToEntity(Json jObj, T e)
    {
        MsJsonReader r = getJsonReader(jObj);
        try
        {
            e.setApplicable(r.readBoolean("applicable"));
            e.setCreated(r.readDate("created"));
            e.setDeleted(r.readDate("deleted"));
            e.setDescription(r.readString("description"));
            e.setExternalCode(r.readString("externalCode"));

            e.setId(r.readUUID("id"));
            e.setMoment(r.readDate("moment"));
            e.setName(r.readString("name"));
            e.setOrganizationId(r.readMetaId("organization", true));
            e.setOwnerId(r.readMetaId("owner", true));
            e.setUpdated(r.readDate("updated"));
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

    }
}
