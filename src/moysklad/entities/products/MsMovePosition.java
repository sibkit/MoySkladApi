package moysklad.entities.products;

import moysklad.entities.common.MsProductPosition;

import java.io.Serializable;
import java.util.UUID;

public class MsMovePosition extends MsProductPosition implements Serializable {
    private UUID moveId;

    public UUID getMoveId() {
        return moveId;
    }

    public void setMoveId(UUID moveId) {
        this.moveId = moveId;
    }
}
