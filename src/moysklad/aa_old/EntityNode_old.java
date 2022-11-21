package moysklad.aa_old;

import moysklad.configuration.nodes.NodeEntry;
import moysklad.entities.MsEntity;
import moysklad.aa_old.mappers_old.MsMapper;

import java.util.ArrayList;
import java.util.List;

public class EntityNode_old
{
    private EntityNode_old parentNode;
    private String nameInParent;
    private MsMapper<? extends MsEntity> mapper;
    private List<NodeEntry> nodeEntries = new ArrayList<>();

    public void addChildNode(String key, EntityNode_old node)
    {
        nodeEntries.add(new NodeEntry(key, node));
        node.parentNode = this;
        node.nameInParent = key;
    }

    public EntityNode_old(MsMapper mapper)
    {
        this.mapper = mapper;
        mapper.setNode(this);
    }

    public EntityNode_old getParentNode()
    {
        return parentNode;
    }

    public List<NodeEntry> getNodeEntries()
    {
        return nodeEntries;
    }

    public MsMapper<? extends MsEntity> getMapper()
    {
        return mapper;
    }
    public void setMapper(MsMapper<? extends MsEntity> mapper)
    {
        this.mapper = mapper;
    }

    public String getNameInParent()
    {
        return nameInParent;
    }
}
