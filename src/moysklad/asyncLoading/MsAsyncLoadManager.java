package moysklad.asyncLoading;

import moysklad.asyncLoading.limits.MsLimitManager;
import moysklad.configuration.nodes.RootNode;
import moysklad.core.*;
import utec.common.events.EventType;

import java.util.*;

public class MsAsyncLoadManager
{
    public static final EventType LOADING_COMPLETE = new EventType("loading_completed");
    public static final EventType LOADING_FAILED = new EventType("loading_failed");
    public static final EventType PART_LOADED = new EventType("part_loaded");

    private static final Object syncKey = new Object();


    private MsLimitManager limitManager;
    private MsDataLoader loader;
    private final int DEF_PAGE_SIZE = 100;
    private List<AsyncLoadEventHandler> handlers = new ArrayList<>();
    private Map<LoadTaskState, List<LoadTask>> tasksMap = new HashMap<>();
    private List<LoadTask> completedUnmarkedTasks = new ArrayList<>();

    private List<LoadTask> getTasksByState(LoadTaskState taskState)
    {
        synchronized (syncKey)
        {
            List<LoadTask> result = tasksMap.get(taskState);

            if(result==null)
            {
                result = new ArrayList<>();
                tasksMap.put(taskState, result);
            }
            return result;
        }
    }

    public MsAsyncLoadManager(MsDataLoader loader)
    {
        this.loader = loader;
        this.limitManager = new MsLimitManager(loader.getConfiguration());
    }

    private void addNewTask(Class entityClass, int offset, int limit)
    {
        MsQuery q = new MsQuery(entityClass,offset,limit);
        LoadTask loadTask = new LoadTask(this);
        loadTask.setQuery(q);
        synchronized (syncKey)
        {
            getTasksByState(LoadTaskState.NEW).add(loadTask);
        }
    }

    private boolean checkForLoading()
    {
        synchronized (syncKey)
        {
            if(getTasksByState(LoadTaskState.NEW).size()>0)
                return true;
            if(getTasksByState(LoadTaskState.RUNNING).size()>0)
                return true;
            return false;
        }
    }

    private void changeTaskState(LoadTask task, LoadTaskState newTaskState)
    {
        getTasksByState(task.getTaskState()).remove(task);
        getTasksByState(newTaskState).add(task);
        task.setTaskState(newTaskState);
    }

    protected void notifyTaskCompleted(MsLoadResult loadResult, LoadTask task)
    {
        synchronized (syncKey)
        {
            int offset = task.getQuery().getOffset();
            int limit = task.getQuery().getLimit();


            if (offset == 0)
            {
                if (offset + limit <= loadResult.getTotalSize())
                {
                    int sp = offset + limit;
                    int ts = loadResult.getTotalSize();
                    while(sp<=ts)
                    {
                        addNewTask(task.getQuery().getEntityClass(), sp, DEF_PAGE_SIZE);
                        sp+=DEF_PAGE_SIZE;
                    }

                }
            }

            completedUnmarkedTasks.add(task);
            limitManager.freeUser(task.getUser());
            firePartLoaded(loadResult.getEntityClass(), loadResult);
        }
    }

    public void load()
    {
        try
        {
            for (RootNode rn : loader.getConfiguration().getEntityNodes())
            {
                addNewTask(rn.getMapper().getEntityClass(), 0, DEF_PAGE_SIZE);
            }

            while (checkForLoading())
            {
                LoadTask[] unmarkedTasksArray;
                synchronized (syncKey)
                {
                    unmarkedTasksArray = new LoadTask[completedUnmarkedTasks.size()];
                    unmarkedTasksArray = completedUnmarkedTasks.toArray(unmarkedTasksArray);
                    completedUnmarkedTasks.clear();
                }
                for (LoadTask uTask : unmarkedTasksArray)
                {
                    changeTaskState(uTask, LoadTaskState.COMPLETED);
                }

                LoadTask[] newTasksArray;
                synchronized (syncKey)
                {
                    List<LoadTask> newTasks = getTasksByState(LoadTaskState.NEW);
                    newTasksArray = new LoadTask[newTasks.size()];
                    newTasksArray = newTasks.toArray(newTasksArray);
                }
                for (LoadTask lt : newTasksArray)
                {
                    MsUser user = limitManager.waitForFreeUser();
                    lt.setUser(user);
                    limitManager.takeUser(user);

                    Thread t = new Thread(lt);
                    changeTaskState(lt, LoadTaskState.RUNNING);
                    t.start();
                }

                Thread.sleep(20);
            }
            fireLoadingComplete();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
/*
    public void load_old()
    {
        try
        {
            for (RootNode rn : loader.getConfiguration().getEntityNodes())
            {
                addNewTask(rn.getMapper().getEntityClass(), 0, DEF_PAGE_SIZE);
            }

            while (checkForLoading())
            {
                synchronized (syncKey)
                {
                    LoadTask[] unmarkedTasksArray = new LoadTask[completedUnmarkedTasks.size()];
                    unmarkedTasksArray = completedUnmarkedTasks.toArray(unmarkedTasksArray);

                    for(LoadTask uTask : unmarkedTasksArray)
                    {
                        changeTaskState(uTask, LoadTaskState.COMPLETED);
                    }
                    completedUnmarkedTasks.clear();

                    List<LoadTask> newTasks = getTasksByState(LoadTaskState.NEW);

                    LoadTask[] newTasksArray = new LoadTask[newTasks.size()];
                    newTasksArray = newTasks.toArray(newTasksArray);

                    for (LoadTask lt : newTasksArray)
                    {
                        if(getTasksByState(LoadTaskState.RUNNING).size()>=maxParallelsCount)
                            break;

                        Thread t = new Thread(lt);
                        changeTaskState(lt, LoadTaskState.RUNNING);
                        t.start();
                    }
                }
                Thread.sleep(20);
            }
            fireLoadingComplete();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
*/
    public MsDataSet getDataSet()
    {
        return loader.getDataSet();
    }

    public MsDataLoader getLoader()
    {
        return loader;
    }

    public void setLoader(MsDataLoader loader)
    {
        this.loader = loader;
    }

    public void addEventHandler(AsyncLoadEventHandler handler)
    {
        handlers.add(handler);
    }

    private void firePartLoaded(Class entityClass, MsLoadResult loadResult)
    {
        for(AsyncLoadEventHandler h : handlers)
            h.onPartLoaded(entityClass, loadResult);
    }

    private void fireLoadingComplete()
    {
        for(AsyncLoadEventHandler h : handlers)
            h.onLoadingComplete();
    }
}
