package com.saleef.mvcpokemonapp.Commons.MvcSkeleton;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public abstract class BaseObservableImpl<ListenerType> extends BaseViewImpl implements ObservableMvc<ListenerType> {

    private  Set<ListenerType> mListenerTypes = new HashSet<>();


    @Override
    public void register(ListenerType listenerType) {
        if (!mListenerTypes.contains(listenerType))
         mListenerTypes.add(listenerType);
    }

    @Override
    public void unregister(ListenerType listenerType) {
        if(mListenerTypes.contains(listenerType))
        mListenerTypes.remove(listenerType);
    }


    protected Set<ListenerType> getListenerTypes(){
        return Collections.unmodifiableSet(mListenerTypes);
    }
}
