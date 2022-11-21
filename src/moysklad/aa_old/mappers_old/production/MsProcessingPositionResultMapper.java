package moysklad.aa_old.mappers_old.production;

import moysklad.core.Json;
import moysklad.aa_old.MsFormatter;
import moysklad.entities.MsEntity;
import moysklad.entities.common.MsProduct;
import moysklad.entities.production.MsProcessingPositionResult;
import moysklad.aa_old.mappers_old.MsMapper;

import java.util.UUID;

public class MsProcessingPositionResultMapper extends MsMapper<MsProcessingPositionResult>
{
    @Override
    public void bindToJson(MsProcessingPositionResult entity, Json jObj)
    {
        MsFormatter f = getFormatter(jObj);
        f.setUUID("id",entity.getId());
        f.setUUID("accountId",entity.getAccountId());
        f.setBigDecimal("quantity",entity.getQuantity());
        f.setMetaId("assortment", MsProduct.class, entity.getProductId());
    }

    @Override
    public void bindToEntity(Json jObj, MsProcessingPositionResult entity)
    {
        MsFormatter f = getFormatter(jObj);
        entity.setId(f.getUUID("id"));
        entity.setAccountId(f.getUUID("accountId"));
        entity.setQuantity(f.getBigDecimal("quantity"));
        entity.setProductId(f.getMetaId("assortment"));
    }

    @Override
    public MsProcessingPositionResult createNewEntity()
    {
        return new MsProcessingPositionResult();
    }

    @Override
    public Class<MsProcessingPositionResult> getEntityClass()
    {
        return MsProcessingPositionResult.class;
    }

    @Override
    public String getMsType()
    {
        return "processingpositionresult";
    }

    @Override
    public UUID getEntityId(MsEntity entity)
    {
        return ((MsProcessingPositionResult)entity).getId();
    }
}
