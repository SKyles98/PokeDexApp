package com.saleef.mvcpokemonapp.Commons.ViewFactory;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.saleef.mvcpokemonapp.Views.DetailsScreen.BottomSheet.AbilitySheet.AbilityBottomSheetViewImpl;
import com.saleef.mvcpokemonapp.Views.DetailsScreen.PokemonDetailsViewImpl;
import com.saleef.mvcpokemonapp.Views.GenScreen.Adapter.ViewPagerAdapter;
import com.saleef.mvcpokemonapp.Views.GenScreen.PokemonGenViewImpl;
import com.saleef.mvcpokemonapp.Views.GenScreen.ViewHolder.PokedexViewHolderImpl;
import com.saleef.mvcpokemonapp.Views.GenScreen.ViewPager.ViewPagerViewImpl;
import com.saleef.mvcpokemonapp.Views.HomeScreen.HomeScreenViewImpl;

//Provides views in a fashion where our controllers dont have to know about the views implementation
public class ViewMvcFactory {



    private final LayoutInflater mLayoutInflater;
    private final ViewPagerAdapter mViewPagerAdapter;
    public ViewMvcFactory(LayoutInflater inflater,ViewPagerAdapter viewPagerAdapter){
        mLayoutInflater = inflater;
        mViewPagerAdapter = viewPagerAdapter;
    }





    public HomeScreenViewImpl getHomeScreenViewImpl(ViewGroup viewGroup){
        return new HomeScreenViewImpl(mLayoutInflater,viewGroup);
    }

    public ViewPagerViewImpl getViewPagerViewImpl(ViewGroup viewGroup){
        return new ViewPagerViewImpl(mLayoutInflater,viewGroup,mViewPagerAdapter);
    }

    public PokedexViewHolderImpl getPokedexViewHolderImpl(ViewGroup viewGroup){
        return new PokedexViewHolderImpl(mLayoutInflater,viewGroup);
    }

    public PokemonGenViewImpl getPokemonGenViewImpl(ViewGroup vieGroup){
        return new PokemonGenViewImpl(mLayoutInflater,vieGroup);
    }

    public PokemonDetailsViewImpl getPokemonDetailsViewImpl(ViewGroup viewGroup){
        return new PokemonDetailsViewImpl(mLayoutInflater,viewGroup);
    }

    public AbilityBottomSheetViewImpl getAbilityBottomSheetViewImpl(ViewGroup viewGroup){
        return new AbilityBottomSheetViewImpl(mLayoutInflater,viewGroup);
    }
}
