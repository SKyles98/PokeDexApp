package com.saleef.mvcpokemonapp;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.saleef.mvcpokemonapp.Commons.Bases.BaseActivity;
import com.saleef.mvcpokemonapp.Commons.FragmentHandler.FragmentFrameWrapper;
import com.saleef.mvcpokemonapp.Commons.ScreenNavigator.ScreenNavigaton;
import com.saleef.mvcpokemonapp.Views.HomeScreen.HomeScreenMvc;

/**
 * This a Pokedex app that will use the pokemon api to show generations of pokemon
 * I will be using Mvc Architecture pattern
 */

/*
TODO Create tabs for each pokemon generation (Tab(All) - Tab(Gen1-8)) done
TODO Add Retrofit networking to get api data will probably need rxjava 2 done
TODO Add search bar functionality to find certain mon (implement filterable) maybe
TODO Detail pokemon screen that shows info about each pokemon (maybe a play sound option to) done
TODO Add bottom sheet pull up in Detail view when user clicks on a ability that will give a ability description done
TODO Future add search bar functionality and maybe bottom navigation to the detail screen to display pokemon stats and moves
 */
public class MainActivity extends BaseActivity implements FragmentFrameWrapper {

     private HomeScreenMvc mHomeScreenMvc;

     private ScreenNavigaton mScreenNavigaton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mHomeScreenMvc = getControllerCompositionRoot().getViewMvcFactory().getHomeScreenViewImpl(null);
        mScreenNavigaton = getControllerCompositionRoot().getScreeNavigation();
        setContentView(mHomeScreenMvc.getRootView());

        if (savedInstanceState==null){
            mScreenNavigaton.navigatetoPokemonGenScreen();
        }
    }


    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onStop() {
        super.onStop();

    }





    @Override
    public FrameLayout getFrameLayout() {
        return mHomeScreenMvc.getFrameLayout();
    }


}