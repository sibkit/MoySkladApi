package moysklad.aa_old;

import moysklad.configuration.MsConfiguration;
import moysklad.core.Json;
import moysklad.entities.MsEntity;
import moysklad.entities.NullTypes;


import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class MsFormatter
{
    private Json jsonObject;
    private MsConfiguration configuration;
    static DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    public MsFormatter(Json jsonObject, MsConfiguration configuration)
    {
        this.jsonObject = jsonObject;
        this.configuration = configuration;
    }



    public void setString(String fieldName, String value)
    {
        if(value!=null)
            jsonObject.set(fieldName,value);
    }

    public void setBigDecimal(String fieldName, BigDecimal value)
    {
        if(value!=null)
            jsonObject.set(fieldName, value);
    }



    public void setInteger(String fieldName, Integer value)
    {
        if(value!=null)
            jsonObject.set(fieldName,value);
    }




    public void setDate(String fieldName, Date value)
    {
        if(value!=null)
            jsonObject.set(fieldName,df.format(value));
    }



    public void setBoolean(String fieldName, Boolean value)
    {
        if(value!=null)
            jsonObject.set(fieldName,value);
    }



    public void setUUID(String fieldName, UUID uuid)
    {
        if(uuid!=null)
        {
            if(uuid.equals(NullTypes.NULL_UUID))
                jsonObject.set(fieldName,Json.nil());
            else
                jsonObject.set(fieldName, uuid.toString());
        }
    }

    public UUID getMetaId()
    {
        Json jMeta = jsonObject.at("meta");
        String href = (String)jMeta.at("href").asString();
        String[] parts = href.split("/");
        return UUID.fromString(parts[parts.length-1]);
    }

    public void setMetaId(Class<? extends MsEntity> entityClass, UUID uuid, String msType)
    {
        //Json jField = Json.object();
        //jsonObject.set(fieldName, jField);

        Json jMeta = Json.object();
        jsonObject.set("meta",jMeta);


        var node = configuration.getNode(entityClass);
        StringBuilder sbHref = new StringBuilder();
        sbHref.append(configuration.getServerApiUrl());
        sbHref.append("/");
        sbHref.append(msType);
        sbHref.append("/");
        sbHref.append(uuid);

        jMeta.set("href",sbHref.toString());

        StringBuilder sbMetaHref = new StringBuilder();
        sbMetaHref.append(configuration.getServerApiUrl());
        sbMetaHref.append("/");
        sbMetaHref.append(msType);
        sbMetaHref.append("/metadata");

        jMeta.set("metadataHref",sbMetaHref.toString());

        jMeta.set("type",msType);
        jMeta.set("mediaType", "application/json");
    }







    public void setMetaId(String fieldName, Class<? extends MsEntity> childClass, UUID uuid, String childMsType)
    {
        Json jField = Json.object();
        jsonObject.set(fieldName, jField);

        Json jMeta = Json.object();
        jField.set("meta",jMeta);


        var node = configuration.getNode(childClass);
        StringBuilder sbHref = new StringBuilder();
        sbHref.append(configuration.getServerApiUrl());
        sbHref.append("/");
        sbHref.append(childMsType);
        sbHref.append("/");
        sbHref.append(uuid);

        jMeta.set("href",sbHref.toString());

        StringBuilder sbMetaHref = new StringBuilder();
        sbMetaHref.append(configuration.getServerApiUrl());
        sbMetaHref.append("/");
        sbMetaHref.append(childMsType);
        sbMetaHref.append("/metadata");

        jMeta.set("metadataHref",sbMetaHref.toString());

        jMeta.set("type",childMsType);
        jMeta.set("mediaType", "application/json");
    }

    public Json getJsonObject()
    {
        return jsonObject;
    }

    public void setJsonObject(Json jsonObject)
    {
        this.jsonObject = jsonObject;
    }
}
