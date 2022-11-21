package moysklad.mapping.purchasing;

import moysklad.core.Json;
import moysklad.core.MsJsonReader;
import moysklad.entities.purchases.MsSupplyPosition;
import moysklad.mapping.common.MsProductPositionMapper;

public class MsSupplyPositionMapper extends MsProductPositionMapper<MsSupplyPosition>
{

    @Override
    public void bindToEntity(Json jObj, MsSupplyPosition entity)
    {
        super.bindToEntity(jObj, entity);

        /*
        private UUID purchaseOrderId;
        private BigDecimal price;
        private BigDecimal vat;
        */
        MsJsonReader jReader = getJsonReader(jObj);
        entity.setSupplyId(getParentId());
        entity.setPrice(jReader.readBigDecimal("price"));
        entity.setVat(jReader.readBigDecimal("vat"));

    }

    @Override
    public MsSupplyPosition createNewEntity()
    {
        return new MsSupplyPosition();
    }

    @Override
    public Class<MsSupplyPosition> getEntityClass()
    {
        return MsSupplyPosition.class;
    }
}
