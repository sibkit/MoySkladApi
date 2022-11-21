package moysklad.core;

import moysklad.configuration.MsConfiguration;
import moysklad.configuration.nodes.EntityNode;
import moysklad.configuration.nodes.NodeEntry;
import moysklad.configuration.nodes.RootNode;
import moysklad.entities.MsEntity;
import moysklad.mapping.MsChildMapper;
import moysklad.mapping.MsEntityMapperBase;

import java.util.List;
import java.util.UUID;

public class MsDataLoader
{
    private final MsConfiguration cfg;
    private final MsUrlBuilder urlBuilder;
    private MsDataSet dataSet;

    private static final Object syncKey = new Object();

    public MsDataLoader(MsConfiguration cfg, MsDataSet dataSet)
    {
        this.cfg = cfg;
        this.dataSet = dataSet;
        urlBuilder = new MsUrlBuilder();
        urlBuilder.setConfiguration(cfg);
    }

    private Json loadJson(String url, MsUser user)
    {
        MsHttpManager httpManager = MsHttpManager.getInstance();
        String jsonString = httpManager.executeGet(url, user);
        return Json.read(jsonString);
    }


    private <T extends MsEntity> void deepLoad(EntityNode node, Json jObj, MsUser user)
    {
        MsEntityMapperBase<T> mapper = node.getMapper();

        //id parentId for mappers

        if(jObj==null)
        {
            throw new RuntimeException("jObj is null [msType: "+node.getMsType()+"]");
        }

        Json jRows = jObj.at("rows");
        if(jRows==null)
            return;

        List<Json> jEntities = jRows.asJsonList();

        for (Json jEntity : jEntities)
        {
            synchronized (syncKey)
            {
                T entity = mapper.createNewEntity();
                mapper.bindToEntity(jEntity, entity);
                dataSet.addEntity(entity);
            }
            for(NodeEntry ne : node.getNodeEntries())
            {
                MsChildMapper cm = (MsChildMapper) ne.getNode().getMapper();
                cm.setParentId(UUID.fromString(jEntity.at("id").asString()));
                deepLoad(ne.getNode(),jEntity.at(ne.getKey()), user);
            }
        }

        if(!(node instanceof RootNode))
        {
            Json jMeta = jObj.at("meta");

            int size = jMeta.at("size").asInteger();
            int limit = jMeta.at("limit").asInteger();
            int offset = jMeta.at("offset").asInteger();

            if (size > (offset + limit))
            {
                String href = urlBuilder.buildUrl(jMeta.at("href").asString(), node, offset + limit, limit);
                Json jAppendObj = loadJson(href, user);
                deepLoad(node, jAppendObj, user);
            }
        }
    }

    public <T extends MsEntity> MsLoadResult loadEntities(MsQuery<T> query, MsUser user)
    {
        RootNode node = (RootNode) getConfiguration().getNode(query.getEntityClass());
        MsHttpManager httpManager = MsHttpManager.getInstance();

        String url = urlBuilder.buildUrl(node.getLoadPath(),node,query.getOffset(),query.getLimit());
        String jsonString = httpManager.executeGet(url, user);
        Json jObj = Json.read(jsonString);


        Json jMeta = jObj.at("meta");
        int totalSize = jMeta.at("size").asInteger();

        int loadedSize = query.getOffset() + query.getLimit();
        if (loadedSize > totalSize)
            loadedSize = totalSize;

        System.out.println("Loaded " + loadedSize + "/" + totalSize);

        deepLoad(node, jObj, user);
        return new MsLoadResult(query.getEntityClass(), query.getOffset(), loadedSize, totalSize);
    }

    /*
    public <T extends MsEntity> void loadEntities(Class<T> entityClass)
    {
        RootNode node = (RootNode) cfg.getNode(entityClass);

        MsEntityMapper<T> mapper = cfg.getNode(entityClass).getMapper();

        MsHttpManager httpManager = MsHttpManager.getInstance();

        int offset = 0;
        int limit = cfg.getDefaultPageSize();
        if(limit > node.getMaxLimit())
            limit = node.getMaxLimit();


        boolean isLoaded = false;
        while (!isLoaded)
        {

            //String url = urlBuilder.buildUrl(query, offset, limit);

            String jsonString = httpManager.executeGet(node.getLoadPath());

            Json jObj = Json.read(jsonString);

            Json jMeta = jObj.at("meta");
            int size = jMeta.at("size").asInteger();

            int loaded = offset + limit;
            if (loaded > size)
                loaded = size;

            System.out.println("Loaded " + loaded + "/" + size);

            if (size <= offset + limit)
                isLoaded = true;
            else
                offset += limit;


            excludeEntities();

            List<Json> jEntities = mapper.getJsonEntities(jObj);

            for (Json jEntity : jEntities)
            {
                //T entity = mapper.createNewEntity();
                //mapper.bindToEntity(jEntity, entity);
                //result.add(entity);
            }
        }

    }

     */
    public MsDataSet getDataSet()
    {
        return dataSet;
    }

    public void setDataSet(MsDataSet dataSet)
    {
        this.dataSet = dataSet;
    }

    public MsConfiguration getConfiguration()
    {
        return cfg;
    }
}
