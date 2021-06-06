package com.saleef.mvcpokemonapp.Views.HomeScreen;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;


import com.saleef.mvcpokemonapp.Commons.MvcSkeleton.BaseObservableImpl;
import com.saleef.mvcpokemonapp.Commons.MvcSkeleton.BaseViewImpl;
import com.saleef.mvcpokemonapp.R;


// Displays our HomeScreen holding only a FrameLayout for fragments to be loaded in
public class HomeScreenViewImpl extends BaseViewImpl implements HomeScreenMvc {


     private FrameLayout mFrameLayout;

     public HomeScreenViewImpl(LayoutInflater layoutInflater, ViewGroup parent){
         setRootView(layoutInflater.inflate(R.layout.activity_main,parent,false));
         mFrameLayout = findViewById(R.id.frameContainer);
     }









    @Override
    public FrameLayout getFrameLayout() {
        return mFrameLayout;
    }


}
