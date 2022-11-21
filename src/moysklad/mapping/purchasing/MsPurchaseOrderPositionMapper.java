package moysklad.mapping.purchasing;


import moysklad.core.Json;
import moysklad.core.MsJsonReader;
import moysklad.entities.common.MsProductPosition;
import moysklad.entities.purchases.MsPurchaseOrderPosition;
import moysklad.mapping.common.MsProductPositionMapper;

public class MsPurchaseOrderPositionMapper extends MsProductPositionMapper<MsPurchaseOrderPosition>
{

    @Override
    public void bindToEntity(Json jObj, MsPurchaseOrderPosition entity)
    {
        super.bindToEntity(jObj, entity);

        /*
        private UUID purchaseOrderId;
        private BigDecimal price;
        private BigDecimal vat;
        */
        MsJsonReader jReader = getJsonReader(jObj);
        entity.setPurchaseOrderId(getParentId());
        entity.setPrice(jReader.readBigDecimal("price"));
        entity.setVat(jReader.readBigDecimal("vat"));

    }

    @Override
    public MsPurchaseOrderPosition createNewEntity()
    {
        return new MsPurchaseOrderPosition();
    }

    @Override
    public Class<MsPurchaseOrderPosition> getEntityClass()
    {
        return MsPurchaseOrderPosition.class;
    }
}
