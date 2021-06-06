package com.saleef.mvcpokemonapp.Views.DetailsScreen;

import com.saleef.mvcpokemonapp.Commons.MvcSkeleton.ObservableMvc;
import com.saleef.mvcpokemonapp.ViewModel.Pokemon;

public interface PokemonDetailViewMvc extends ObservableMvc<PokemonDetailViewMvc.Listener> {

    interface Listener{
        void onAbilityButtonClicked(String abilityUrl);
    }


    void showProgressIndication();
    void hideProgressIndication();
    void bindPokemonDetails(Pokemon pokemon);
}
