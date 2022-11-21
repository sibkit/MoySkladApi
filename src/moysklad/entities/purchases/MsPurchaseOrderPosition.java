package moysklad.entities.purchases;

import moysklad.entities.common.MsProductPosition;

import java.math.BigDecimal;
import java.util.UUID;

public class MsPurchaseOrderPosition extends MsProductPosition
{
    private UUID purchaseOrderId;
    private BigDecimal price;
    private BigDecimal vat;

    public UUID getPurchaseOrderId()
    {
        return purchaseOrderId;
    }

    public void setPurchaseOrderId(UUID purchaseOrderId)
    {
        this.purchaseOrderId = purchaseOrderId;
    }

    public BigDecimal getPrice()
    {
        return price;
    }

    public void setPrice(BigDecimal price)
    {
        this.price = price;
    }

    public BigDecimal getVat()
    {
        return vat;
    }

    public void setVat(BigDecimal vat)
    {
        this.vat = vat;
    }
}
