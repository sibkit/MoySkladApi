package moysklad.configuration.nodes;

public class NodeEntry
{
    private String key;
    private ChildNode node;

    public NodeEntry(String key, ChildNode node)
    {
        this.key = key;
        this.node = node;
    }

    public String getKey()
    {
        return key;
    }

    public ChildNode getNode()
    {
        return node;
    }
}
