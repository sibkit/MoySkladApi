package moysklad.asyncLoading;

import moysklad.core.MsLoadResult;
import moysklad.core.MsQuery;
import moysklad.core.MsUser;

public class LoadTask implements Runnable
{
    private MsQuery query;
    private MsAsyncLoadManager loadManager;
    private LoadTaskState taskState = LoadTaskState.NEW;
    private MsUser user;


    public LoadTask(MsAsyncLoadManager loadManager)
    {
        this.loadManager = loadManager;
    }

    @Override
    public void run()
    {
        MsLoadResult lr = loadManager.getLoader().loadEntities(query, getUser());
        loadManager.notifyTaskCompleted(lr,this);
    }

    public MsQuery getQuery()
    {
        return query;
    }

    public void setQuery(MsQuery query)
    {
        this.query = query;
    }

    public LoadTaskState getTaskState()
    {
        return taskState;
    }

    public void setTaskState(LoadTaskState taskState)
    {
        this.taskState = taskState;
    }

    public MsUser getUser()
    {
        return user;
    }

    public void setUser(MsUser user)
    {
        this.user = user;
    }
}
