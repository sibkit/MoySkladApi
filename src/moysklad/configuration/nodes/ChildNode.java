package moysklad.configuration.nodes;

import moysklad.mapping.MsEntityMapperBase;

public class ChildNode extends EntityNode
{
    private EntityNode parentNode;

    public ChildNode(String msType, MsEntityMapperBase mapper)
    {
        super(msType,mapper);
    }

    public EntityNode getParentNode()
    {
        return parentNode;
    }

    protected void setParentNode(EntityNode parentNode)
    {
        this.parentNode = parentNode;
    }
}
