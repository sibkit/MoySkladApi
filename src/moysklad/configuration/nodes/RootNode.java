package moysklad.configuration.nodes;

import moysklad.entities.MsEntity;
import moysklad.mapping.MsEntityMapperBase;


public class RootNode extends EntityNode
{
    private String loadPath;

    public RootNode(String msType, MsEntityMapperBase<? extends MsEntity> mapper, String loadPath)
    {
        super(msType, mapper);
        this.loadPath = loadPath;
    }

    public String getLoadPath()
    {
        return loadPath;
    }
    public void setLoadPath(String loadPath)
    {
        this.loadPath = loadPath;
    }
}
