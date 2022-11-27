package moysklad.entities.purchases;

import moysklad.entities.MsDocument;

import java.io.Serializable;
import java.util.UUID;

public class MsSupply extends MsDocument implements Serializable
{
    private UUID agentId;
    private UUID storeId;

    public UUID getAgentId()
    {
        return agentId;
    }
    public void setAgentId(UUID agentId)
    {
        this.agentId = agentId;
    }

    public UUID getStoreId()
    {
        return storeId;
    }
    public void setStoreId(UUID storeId)
    {
        this.storeId = storeId;
    }
}
