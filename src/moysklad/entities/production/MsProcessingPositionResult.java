package moysklad.entities.production;

import moysklad.entities.MsEntity;
import moysklad.entities.common.MsProductPosition;

import java.math.BigDecimal;
import java.util.UUID;

public class MsProcessingPositionResult extends MsProductPosition
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
