package moysklad.entities.production;

import moysklad.entities.common.MsProductPosition;

import java.util.UUID;

public class MsProcessingPositionMaterial extends MsProductPosition
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
