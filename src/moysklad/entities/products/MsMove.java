package moysklad.entities.products;

import moysklad.entities.MsDocument;

import java.io.Serializable;
import java.util.UUID;

public class MsMove extends MsDocument implements Serializable {

    private UUID sourceStoreId;
    private UUID targetStoreId;

    public UUID getSourceStoreId() {
        return sourceStoreId;
    }

    public void setSourceStoreId(UUID sourceStoreId) {
        this.sourceStoreId = sourceStoreId;
    }

    public UUID getTargetStoreId() {
        return targetStoreId;
    }

    public void setTargetStoreId(UUID targetStoreId) {
        this.targetStoreId = targetStoreId;
    }
}
