package moysklad.core;

import moysklad.configuration.MsConfiguration;
import moysklad.configuration.nodes.EntityNode;
import moysklad.configuration.nodes.NodeEntry;
import moysklad.entities.MsEntity;

public class MsUrlBuilder
{
    private MsConfiguration cfg;

    void buildExpandPath(StringBuilder sb, NodeEntry e)
    {
        sb.append(e.getKey());
        for(NodeEntry cne : e.getNode().getNodeEntries())
        {
            sb.append(e.getKey());
            sb.append(".");
            buildExpandPath(sb, cne);
        }
    }

    public String buildUrl(String baseUrl, EntityNode node, int offset, int limit)
    {
        StringBuilder sb = new StringBuilder(baseUrl);

        sb.append("?offset=");
        sb.append(offset);
        sb.append("&limit=");
        sb.append(limit);

        if(node.getNodeEntries().size()>0)
        {
            sb.append("&expand=");
            for(NodeEntry cne: node.getNodeEntries())
            {
                buildExpandPath(sb,cne);
                sb.append(",");
            }
            sb.deleteCharAt(sb.length()-1);
        }
        return sb.toString();
    }

/*
    public String buildCascadeLoadUrl(Class eClass, int offset, int limit)
    {
        StringBuilder sb = new StringBuilder();
        sb.append(cfg.getServerApiUrl());

        MsMapper mapper = cfg.getMapper(eClass);

        sb.append(mapper.getServerLoadPath());
        sb.append("?");
        sb.append("offset=");
        sb.append(offset);
        sb.append("&");
        sb.append("limit=");
        sb.append(limit);

        EntityNode_old en = cfg.getNode(eClass);
        if(en.getParentNode()!=null)
            throw new RuntimeException("For cascade loading, entity must be root");

        if(en.getNodeEntries().size()>0)
        {
            sb.append("&expand=");
            for(NodeEntry cne: en.getNodeEntries())
            {
                buildExpand(sb,cne);
                sb.append(",");
            }
            sb.deleteCharAt(sb.length()-1);
        }



        return sb.toString();
    }
*/
    /*
    public <T extends MsEntity> String buildUrl(MsQuery query, int offset, int limit)
    {
        Class<T> eClass = query.getEntityClass();

        StringBuilder sb = new StringBuilder();
        sb.append(cfg.getServerApiUrl());
        //sb.append("/");

        MsMapper mapper = cfg.getMapper(eClass);

        mapper.setParentId(query.getParentId());

        sb.append(mapper.getServerLoadPath());

        sb.append("&");

        sb.append("offset=");
        sb.append(offset);

        sb.append("?");

        sb.append("limit=");
        sb.append(limit);

        return sb.toString();
    }
*/

    /*
    public <T extends MsEntity> String buildUpdateUrl(MsEntity entity)
    {
        EntityNode_old eNode = getConfiguration().getNode(entity.getClass());
        if(eNode.getParentNode()!=null)
            throw new RuntimeException("for entity '"+entity.getClass().getCanonicalName()+"' found parent EntityNode");

        StringBuilder sb = new StringBuilder();
        sb.append(getConfiguration().getServerApiUrl());
        sb.append("/");
        sb.append(eNode.getMapper().getMsType());
        sb.append("/");
        sb.append((eNode.getMapper()).getEntityId(entity).toString());
        //sb.append(entity.getId().toString());
        return sb.toString();
    }
*/

    String buildLoadUrl(Class<? extends MsEntity> entityClass, int offset, int limit)
    {

        StringBuilder sb = new StringBuilder();
        sb.append(cfg.getServerApiUrl());
        sb.append("/");
        sb.append(cfg.getMsClassName(entityClass));
        sb.append("?limit=");
        sb.append(limit);
        sb.append("&offset=");
        sb.append(offset);
        return sb.toString();
    }

    /*
    String buildLoadChildUrl(Class<? extends MsEntity> entityClass, UUID parentId, int offset, int limit)
    {
        EntityNode_old eNode = cfg.getNode(entityClass);
        EntityNode_old eParentNode = eNode.getParentNode();

        if(eParentNode==null)
            throw new RuntimeException("for entity '"+entityClass.getCanonicalName()+"' not found parent EntityNode");

        StringBuilder sb = new StringBuilder();
        sb.append(cfg.getServerApiUrl());
        sb.append("/");
        sb.append(eParentNode.getMapper().getMsType());
        sb.append("/");
        sb.append(parentId.toString());
        sb.append("/");
        sb.append(eNode.getNameInParent());
        sb.append("?limit=");
        sb.append(limit);
        sb.append("&offset=");
        sb.append(offset);
        return sb.toString();
    }
*/
/*
    String buildUpdateChildUrl(Class<? extends MsEntity> entityClass, UUID entityId, UUID parentId)
    {
        EntityNode_old eNode = cfg.getNode(entityClass);
        EntityNode_old eParentNode = eNode.getParentNode();

        if(eParentNode==null)
            throw new RuntimeException("for entity '"+entityClass.getCanonicalName()+"' not found parent EntityNode");

        StringBuilder sb = new StringBuilder();
        sb.append(cfg.getServerApiUrl());
        sb.append("/");
        sb.append(eParentNode.getMapper().getMsType());
        sb.append("/");
        sb.append(parentId.toString());
        sb.append("/");
        sb.append(eNode.getNameInParent());
        sb.append("/");
        sb.append(entityId.toString());
        return sb.toString();
    }
*/

    public MsConfiguration getConfiguration()
    {
        return cfg;
    }

    public void setConfiguration(MsConfiguration cfg)
    {
        this.cfg = cfg;
    }
}
