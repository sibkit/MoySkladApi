package moysklad.entities.purchases;


import moysklad.entities.MsDocument;

import java.util.UUID;

public class MsPurchaseOrder extends MsDocument
{
    private UUID storeId;
    private UUID agentId;

    public UUID getStoreId()
    {
        return storeId;
    }
    public void setStoreId(UUID storeId)
    {
        this.storeId = storeId;
    }

    public UUID getAgentId()
    {
        return agentId;
    }
    public void setAgentId(UUID agentId)
    {
        this.agentId = agentId;
    }

}
