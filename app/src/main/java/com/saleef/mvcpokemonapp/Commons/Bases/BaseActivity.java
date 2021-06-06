package com.saleef.mvcpokemonapp.Commons.Bases;



import androidx.appcompat.app.AppCompatActivity;

import com.saleef.mvcpokemonapp.Commons.DependencyInjection.ActivityCompositionRoot;
import com.saleef.mvcpokemonapp.Commons.DependencyInjection.ControllerCompositionRoot;
import com.saleef.mvcpokemonapp.CustomApplication;

public class BaseActivity extends AppCompatActivity {
    private ActivityCompositionRoot mActivityCompositionRoot;
    private ControllerCompositionRoot mControllerCompositionRoot;


    protected ControllerCompositionRoot getControllerCompositionRoot() {
        if (mControllerCompositionRoot==null){
            mControllerCompositionRoot = new ControllerCompositionRoot(getActivityCompositionRoot());
        }
        return mControllerCompositionRoot;
    }

    public ActivityCompositionRoot getActivityCompositionRoot() {
        if (mActivityCompositionRoot==null){
            mActivityCompositionRoot = new ActivityCompositionRoot(this,this,((CustomApplication) getApplication()).getCompositionRoot());
        }
        return mActivityCompositionRoot;
    }
}
