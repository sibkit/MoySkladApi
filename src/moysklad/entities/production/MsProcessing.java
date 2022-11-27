package moysklad.entities.production;

import moysklad.entities.MsDocument;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

public class MsProcessing extends MsDocument implements Serializable
{
    private UUID materialsStoreId;
    private UUID productsStoreId;

    private UUID processingPlanId;

    private BigDecimal quantity;

    public UUID getMaterialsStoreId()
    {
        return materialsStoreId;
    }
    public void setMaterialsStoreId(UUID materialsStoreId)
    {
        this.materialsStoreId = materialsStoreId;
    }

    public UUID getProductsStoreId()
    {
        return productsStoreId;
    }
    public void setProductsStoreId(UUID productsStoreId)
    {
        this.productsStoreId = productsStoreId;
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
