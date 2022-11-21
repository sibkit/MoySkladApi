package moysklad.entities.production;

import moysklad.entities.MsDocument;

import java.math.BigDecimal;
import java.util.UUID;

public class MsProcessingOrder extends MsDocument
{
    private UUID storeId;
    private BigDecimal quantity;
    private UUID processingPlanId;

    public UUID getStoreId()
    {
        return storeId;
    }
    public void setStoreId(UUID storeId)
    {
        this.storeId = storeId;
    }

    public BigDecimal getQuantity()
    {
        return quantity;
    }
    public void setQuantity(BigDecimal quantity)
    {
        this.quantity = quantity;
    }

    public UUID getProcessingPlanId()
    {
        return processingPlanId;
    }

    public void setProcessingPlanId(UUID processingPlanId)
    {
        this.processingPlanId = processingPlanId;
    }
}
