package moysklad.entities.production;

import moysklad.entities.common.MsProductPosition;

import java.util.UUID;

public class MsProcessingPlanResult  extends MsProductPosition
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
