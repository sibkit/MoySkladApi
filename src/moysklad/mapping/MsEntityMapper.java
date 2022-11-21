package moysklad.mapping;

import moysklad.configuration.nodes.EntityNode;
import moysklad.core.Json;
import moysklad.entities.MsEntity;

public interface MsEntityMapper<T extends MsEntity>
{
    void bindToEntity(Json jObj, T entity);
    T createNewEntity();

    Class<T> getEntityClass();

    EntityNode getNode();
    void setNode(EntityNode node);
}
