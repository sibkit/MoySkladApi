package moysklad.mapping;

import java.util.UUID;

public interface MsChildMapper
{
    UUID getParentId();
    void setParentId(UUID parentId);
}
