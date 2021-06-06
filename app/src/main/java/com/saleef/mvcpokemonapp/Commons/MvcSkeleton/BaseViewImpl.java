package com.saleef.mvcpokemonapp.Commons.MvcSkeleton;

import android.content.Context;
import android.view.View;

public abstract class BaseViewImpl implements ViewMvc {

    private View rootView;
    @Override
    public View getRootView() {
        return rootView;
    }


    protected void setRootView(View view){
        rootView = view;
    }

   protected Context getContext(){
       return  getRootView().getContext();
   }



    protected <T extends View> T findViewById(int id){
       return  getRootView().findViewById(id);
    }
}
