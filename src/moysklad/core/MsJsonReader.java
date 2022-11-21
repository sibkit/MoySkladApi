package moysklad.core;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class MsJsonReader
{
    private Json jsonObject;
    static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");



    public String readString(String fieldName)
    {
        Json jString = getJsonObject().at(fieldName);
        if(jString!=null)
        {
            if(jString.isString())
                return jString.asString();
            else
                return jString.toString();
        }
        return null;
    }

    public BigDecimal readBigDecimal(String fieldName)
    {
        Json jBigDecimal = getJsonObject().at(fieldName);
        if(jBigDecimal!=null)
            return jBigDecimal.asBigDecimal();
        return null;
    }

    public Integer readInteger(String fieldName)
    {
        Json jInteger = getJsonObject().at(fieldName);
        if(jInteger!=null)
            return jInteger.asInteger();
        return null;
    }

    public Date readDate(String fieldName)
    {
        Json jDate = getJsonObject().at(fieldName);
        if (jDate == null)
            return null;

        String sDate = jDate.asString();
        if (sDate == null)
            return null;

        try
        {
            Date result = df.parse(sDate);
            return result;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        return null;
    }

    public Boolean readBoolean(String fieldName)
    {
        Json jBool = getJsonObject().at(fieldName);
        if(jBool!=null)
            return jBool.asBoolean();
        return null;
    }

    public UUID readUUID(String fieldName)
    {
        String sResult = getJsonObject().at(fieldName).asString();
        if(sResult==null)
            return null;
        return UUID.fromString(sResult);
    }

    public UUID readMetaId(String fieldName, boolean required)
    {
        Json jObj = getJsonObject();
        Json jField = jObj.at(fieldName);
        if(jField==null)
        {
            if(required)
                throw new RuntimeException("field \"" + fieldName + "\" not exits");
            else
                return null;
            //throw new RuntimeException(fieldName + " not exits");
            //return null;
        }

        Json jMeta = jField.at("meta");
        String href = (String)jMeta.at("href").asString();
        String[] parts = href.split("/");
        return UUID.fromString(parts[parts.length-1]);
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
