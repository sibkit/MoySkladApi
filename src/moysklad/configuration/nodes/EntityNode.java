package moysklad.configuration.nodes;

import moysklad.entities.MsEntity;
import moysklad.mapping.MsEntityMapperBase;

import java.util.ArrayList;
import java.util.List;

public abstract class EntityNode {
    private MsEntityMapperBase mapper;
    private final List<NodeEntry> nodeEntries = new ArrayList<>();
    private final String msType;

    public EntityNode(String msType, MsEntityMapperBase mapper) {
        this.msType = msType;
        this.mapper = mapper;
    }

    public <T extends MsEntity> MsEntityMapperBase<T> getMapper()
    {
        return mapper;
    }

    public List<NodeEntry> getNodeEntries()
    {
        return nodeEntries;
    }

    public void addNode(String key, ChildNode node) {
        this.nodeEntries.add(new NodeEntry(key, node));
        node.setParentNode(this);
    }

    public String getMsType()
    {
        return msType;
    }
}
