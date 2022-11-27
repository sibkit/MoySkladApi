package moysklad.entities.production;

import moysklad.entities.MsEntity;
import moysklad.entities.common.MsProductPosition;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

public class MsProcessingPositionResult extends MsProductPosition implements Serializable
{
    private UUID processingId;

    public UUID getProcessingId()
    {
        return processingId;
    }

    public void setProcessingId(UUID processingId)
    {
        this.processingId = processingId;
    }
}
