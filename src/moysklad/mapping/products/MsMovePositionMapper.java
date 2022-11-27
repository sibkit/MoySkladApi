package moysklad.mapping.products;

import moysklad.core.Json;
import moysklad.core.MsJsonReader;
import moysklad.entities.products.MsMovePosition;
import moysklad.entities.purchases.MsPurchaseOrderPosition;
import moysklad.mapping.common.MsProductPositionMapper;

public class MsMovePositionMapper extends MsProductPositionMapper<MsMovePosition> {

    @Override
    public void bindToEntity(Json jObj, MsMovePosition entity) {
        super.bindToEntity(jObj, entity);
        MsJsonReader jReader = getJsonReader(jObj);
        entity.setMoveId(getParentId());
    }

    @Override
    public MsMovePosition createNewEntity() {
        return new MsMovePosition();
    }

    @Override
    public Class<MsMovePosition> getEntityClass() {
        return MsMovePosition.class;
    }
}
