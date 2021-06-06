package com.saleef.mvcpokemonapp.Commons.ScreenNavigator;

import com.saleef.mvcpokemonapp.Commons.FragmentHandler.FragmentHandler;
import com.saleef.mvcpokemonapp.ViewModel.Pokemon;
import com.saleef.mvcpokemonapp.Views.DetailsScreen.BottomSheet.AbilitySheet.AbilityBottomSheetDialog;
import com.saleef.mvcpokemonapp.Views.DetailsScreen.PokemonDetailsFragment;
import com.saleef.mvcpokemonapp.Views.GenScreen.ViewPager.ViewPagerFragment;

public class ScreenNavigaton {


    private final FragmentHandler mFragmentManager;
    public ScreenNavigaton(FragmentHandler manager){
          mFragmentManager = manager;
    }

    private FragmentHandler getFragmentManager(){
       return mFragmentManager;
    }


   public void navigatetoPokemonGenScreen(){
        getFragmentManager().replaceFragment(ViewPagerFragment.newInstance());
   }

    public void navigateToPokemonDetails(Pokemon pokemon){
         getFragmentManager().replaceFragment(PokemonDetailsFragment.newInstance(pokemon));
    }

    public void OpenAbilityBottomSheet(String abilityUrl){
         getFragmentManager().openSheet(AbilityBottomSheetDialog.newInstance(abilityUrl));
    }



}
