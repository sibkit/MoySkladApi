package moysklad.aa_old;

import moysklad.configuration.MsConfiguration;
import moysklad.core.Json;
import moysklad.core.MsHttpManager;
import moysklad.core.MsUrlBuilder;
import moysklad.entities.MsEntity;
import moysklad.aa_old.mappers_old.MsMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MsDataManager_old
{
    private static MsDataManager_old instance;
    public static MsDataManager_old getInstance()
    {
        if(instance==null)
        {
            instance = new MsDataManager_old();
            instance.setConfiguration(MsConfiguration.createConfiguration());
            instance.getConfiguration().setServerApiUrl("https://online.moysklad.ru/api/remap/1.2/entity");
            instance.getConfiguration().setAccountId("13a89ca6-38ea-11e9-9109-f8fc00011c68");
        }
        return instance;
    }

    private MsConfiguration configuration;
    private MsUrlBuilder urlBuilder = new MsUrlBuilder();


    private Json loadJson(String url)
    {
        MsHttpManager httpManager = MsHttpManager.getInstance();
        String jsonString = httpManager.executeGet(url);
        return Json.read(jsonString);
    }

    private  <T extends MsEntity> List<T> excludeEntities(Class<? extends MsEntity> entityClass, Json jObj)
    {
        MsMapper<T> mapper = configuration.getMapper(entityClass);
        List<Json> jEntities = mapper.getJsonEntities(jObj);
        List<T> result = new ArrayList<>();
        for (Json jEntity : jEntities)
        {
            T entity = mapper.createNewEntity();
            mapper.bindToEntity(jEntity, entity);
            result.add(entity);
        }
        return result;
    }

    public <T extends MsEntity> List<T> loadChildEntities(Class<? extends MsEntity> entityClass, UUID parentId, int offset, int limit)
    {
        Json jObj = loadJson(urlBuilder.buildLoadChildUrl(entityClass, parentId, offset, limit));
        return excludeEntities(entityClass,jObj);
    }

    public <T extends MsEntity> List<T> loadAllEntities(Class<? extends MsEntity> entityClass)
    {
        MsMapper<T> mapper = getConfiguration().getMapper(entityClass);
        MsHttpManager httpManager = MsHttpManager.getInstance();

        List<T> result = new ArrayList<>();

        int offset = 0;
        int limit = 100;

        boolean isLoaded = false;
        while (!isLoaded)
        {

            String url = urlBuilder.buildLoadUrl(entityClass, offset, limit);
            String jsonString = httpManager.executeGet(url);

            Json jObj = Json.read(jsonString);

            Json jMeta = jObj.at("meta");
            int size = jMeta.at("size").asInteger();

            int loaded = offset+limit;
            if(loaded > size)
                loaded = size;

            System.out.println("Loaded " + loaded + "/" + size);

            if (size <= offset + limit)
                isLoaded = true;
            else
                offset += limit;


            List<Json> jEntities = mapper.getJsonEntities(jObj);

            for (Json jEntity : jEntities)
            {
                T entity = mapper.createNewEntity();
                mapper.bindToEntity(jEntity, entity);
                result.add(entity);
            }
        }
        return result;
    }

    public <T extends MsEntity> List<T> loadEntities(Class<? extends MsEntity> entityClass, int offset, int limit)
    {
        Json jObj = loadJson(urlBuilder.buildLoadUrl(entityClass, offset, limit));
        return excludeEntities(entityClass,jObj);
    }

    public void updateEntity(MsEntity entity)
    {
        MsMapper mapper = getConfiguration().getMapper(entity.getClass());
        String url = urlBuilder.buildUpdateUrl(entity);
        Json jObj = Json.object();
        mapper.bindToJson(entity, jObj);

        MsHttpManager httpManager = MsHttpManager.getInstance();
        String result = httpManager.executePut(url,jObj.toString());
        System.out.println(result);
    }

    public MsConfiguration getConfiguration()
    {
        return configuration;
    }

    public void setConfiguration(MsConfiguration configuration)
    {
        this.configuration = configuration;
        urlBuilder.setConfiguration(configuration);
    }
}
