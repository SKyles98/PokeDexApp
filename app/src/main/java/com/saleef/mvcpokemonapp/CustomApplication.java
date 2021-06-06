package com.saleef.mvcpokemonapp;

import android.app.Application;

import com.saleef.mvcpokemonapp.Commons.DependencyInjection.CompositionRoot;

public class CustomApplication extends Application {

   private CompositionRoot mCompositionRoot;
    @Override
    public void onCreate() {
        super.onCreate();
        mCompositionRoot = new CompositionRoot();
    }


    public CompositionRoot getCompositionRoot() {
        return mCompositionRoot;
    }
}
