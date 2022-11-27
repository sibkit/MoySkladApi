package moysklad.entities.production;

import moysklad.entities.common.MsProductPosition;

import java.io.Serializable;
import java.util.UUID;

public class MsProcessingPlanResult  extends MsProductPosition implements Serializable
{
    private UUID processingPlanId;

    public UUID getProcessingPlanId()
    {
        return processingPlanId;
    }

    public void setProcessingPlanId(UUID processingPlanId)
    {
        this.processingPlanId = processingPlanId;
    }
}
