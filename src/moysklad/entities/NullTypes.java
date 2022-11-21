package moysklad.entities;

import java.util.Date;
import java.util.UUID;

public interface NullTypes
{
    UUID NULL_UUID = UUID.fromString("00000000-0000-0000-0000-000000000000");
    Date NULL_DATE = new Date(0);
}
