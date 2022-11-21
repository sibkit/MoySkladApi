package reports;

import java.math.BigDecimal;

public class GroupItem
{
    private String productName;
    private BigDecimal quantity = new BigDecimal(0);
    private String groupPath;
    private String code;

    public String getProductName()
    {
        return productName;
    }

    public void setProductName(String productName)
    {
        this.productName = productName;
    }

    public BigDecimal getQuantity()
    {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity)
    {
        this.quantity = quantity;
    }

    public String getGroupPath()
    {
        return groupPath;
    }

    public void setGroupPath(String groupPath)
    {
        this.groupPath = groupPath;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }
}
