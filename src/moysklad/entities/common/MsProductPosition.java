package moysklad.entities.common;

import moysklad.entities.MsEntity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

public class MsProductPosition implements MsEntity, Serializable
{
    private UUID id;
    private UUID productId;
    private BigDecimal quantity;


    public UUID getId()
    {
        return id;
    }

    public void setId(UUID id)
    {
        this.id = id;
    }


    public UUID getProductId()
    {
        return productId;
    }

    public void setProductId(UUID productId)
    {
        this.productId = productId;
    }

    public BigDecimal getQuantity()
    {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity)
    {
        this.quantity = quantity;
    }


}