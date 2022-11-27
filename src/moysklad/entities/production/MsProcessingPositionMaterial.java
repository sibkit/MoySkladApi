package moysklad.entities.production;

import moysklad.entities.common.MsProductPosition;

import java.io.Serializable;
import java.util.UUID;

public class MsProcessingPositionMaterial extends MsProductPosition implements Serializable
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
