package moysklad.entities.purchases;

import moysklad.entities.common.MsProductPosition;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

public class MsSupplyPosition extends MsProductPosition implements Serializable
{
    private UUID supplyId;
    private BigDecimal price;
    private BigDecimal vat;


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

    public UUID getSupplyId()
    {
        return supplyId;
    }

    public void setSupplyId(UUID supplyId)
    {
        this.supplyId = supplyId;
    }
}
