package com.saleef.mvcpokemonapp.Commons.DependencyInjection;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;


import com.saleef.mvcpokemonapp.UseCases.FetchAbilityDetailUseCase;
import com.saleef.mvcpokemonapp.UseCases.FetchPokemonDetailsUseCase;
import com.saleef.mvcpokemonapp.UseCases.FetchPokemonUseCase;
import com.saleef.mvcpokemonapp.Commons.FragmentHandler.FragmentFrameWrapper;
import com.saleef.mvcpokemonapp.Commons.FragmentHandler.FragmentHandler;
import com.saleef.mvcpokemonapp.Commons.ScreenNavigator.ScreenNavigaton;
import com.saleef.mvcpokemonapp.Caching.SharedPrefs;
import com.saleef.mvcpokemonapp.Commons.ViewFactory.ViewMvcFactory;
import com.saleef.mvcpokemonapp.Views.GenScreen.Adapter.ViewPagerAdapter;

public class ControllerCompositionRoot {

  private final ActivityCompositionRoot mActivityCompositionRoot;

    public ControllerCompositionRoot(ActivityCompositionRoot activityCompositionRoot){
         mActivityCompositionRoot = activityCompositionRoot;
    }

    private ActivityCompositionRoot getActivityCompositionRoot(){
        return mActivityCompositionRoot;
    }

    private FragmentActivity getFragmentActivity(){
        return getActivityCompositionRoot().getFragmentActivity();
    }
    private FragmentManager getFragmentManager(){
        return getFragmentActivity().getSupportFragmentManager();
    }

    private FragmentHandler getFragmentHandler(){
        return new FragmentHandler(getFragmentManager(),getFragmentFrameWrapper());
    }

    private FragmentFrameWrapper getFragmentFrameWrapper(){
           return (FragmentFrameWrapper) getFragmentActivity();
    }
  private LayoutInflater getLayoutInflater(){
      return LayoutInflater.from(getActivityCompositionRoot().getFragmentActivity());
  }

  public ViewMvcFactory getViewMvcFactory(){
        return new ViewMvcFactory(getLayoutInflater(),getViewPagerAdapter());
  }

  public ViewPagerAdapter getViewPagerAdapter(){
        return new ViewPagerAdapter(getFragmentManager(),getFragmentActivity().getLifecycle());
  }


  public FetchPokemonUseCase getFetchPokemonUseCase(){
        return new FetchPokemonUseCase(getActivityCompositionRoot()
                .getCompositionRoot().getPokemonApi(),getSharedPrefs());
  }

  public FetchPokemonDetailsUseCase getFetchPokemonDetailsUseCase(){
        return new FetchPokemonDetailsUseCase(getActivityCompositionRoot()
                .getCompositionRoot().getPokemonApi());
  }
  public FetchAbilityDetailUseCase getFetchAbilityDetailsUseCase(){
        return new FetchAbilityDetailUseCase(getActivityCompositionRoot()
                .getCompositionRoot().getPokemonApi());
  }

  public ScreenNavigaton getScreeNavigation(){
        return new ScreenNavigaton(getFragmentHandler());
  }

  private SharedPreferences getSharedPreferences(){
        return getFragmentActivity().getSharedPreferences(getFragmentActivity().getPackageName(), Context.MODE_PRIVATE);
  }

  private SharedPrefs getSharedPrefs(){
        return new SharedPrefs(getSharedPreferences());
  }

}
