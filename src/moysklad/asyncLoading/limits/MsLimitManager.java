package moysklad.asyncLoading.limits;

import moysklad.configuration.MsConfiguration;
import moysklad.core.MsUser;

import java.util.HashMap;
import java.util.Map;

public class MsLimitManager
{
    private MsConfiguration configuration;

    int maxParallelsPerUser = 5;

    private static final Object syncKey = new Object();

    Map<MsUser, Integer> userParallelsMap = new HashMap<>();

    public MsLimitManager(MsConfiguration configuration)
    {
        this.configuration = configuration;
        for(MsUser user: configuration.getUsers())
            userParallelsMap.put(user,0);
    }

    public void freeUser(MsUser user)
    {
        synchronized (syncKey)
        {
            userParallelsMap.put(user,userParallelsMap.get(user)-1);
        }
    }

    public void takeUser(MsUser user)
    {
        synchronized (syncKey)
        {
            userParallelsMap.put(user,userParallelsMap.get(user)+1);
        }
    }

    public MsUser waitForFreeUser()
    {
        try
        {
            MsUser freeUser = null;
            while (true)
            {
                freeUser = getFreeUser();
                if(freeUser!=null)
                    return freeUser;
                Thread.sleep(20);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public MsUser getFreeUser()
    {
        for(Map.Entry<MsUser, Integer> entry : userParallelsMap.entrySet())
        {
            if(entry.getValue()<maxParallelsPerUser)
            {
                return entry.getKey();
            }
        }
        return null;
    }


}
