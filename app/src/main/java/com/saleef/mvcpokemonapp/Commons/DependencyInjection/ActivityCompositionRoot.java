package com.saleef.mvcpokemonapp.Commons.DependencyInjection;



import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

public class ActivityCompositionRoot {


    private final CompositionRoot mCompositionRoot;
    private final FragmentActivity mFragmentActivity;
    private final AppCompatActivity mAppCompatActivity;



    public ActivityCompositionRoot(FragmentActivity activity, AppCompatActivity appCompatActivity, CompositionRoot compositionRoot){
           mAppCompatActivity = appCompatActivity;
           mFragmentActivity = activity;
           mCompositionRoot = compositionRoot;
    }

    public CompositionRoot getCompositionRoot() {
        return mCompositionRoot;
    }

    public FragmentActivity getFragmentActivity() {
        return mFragmentActivity;
    }

    public AppCompatActivity getAppCompatActivity() {
        return mAppCompatActivity;
    }
}
