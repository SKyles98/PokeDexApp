package com.saleef.mvcpokemonapp.Commons.MvcSkeleton;

// Base functionality for any interface that wants to be observed by others
public interface ObservableMvc<ListenerType> extends ViewMvc {

    void register(ListenerType listenerType);
    void unregister(ListenerType listenerType);

}
