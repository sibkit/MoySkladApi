package moysklad.asyncLoading;

import moysklad.core.MsLoadResult;

public interface AsyncLoadEventHandler
{
    void onPartLoaded(Class entityClass, MsLoadResult loadResult);
    void onLoadingComplete();
}
