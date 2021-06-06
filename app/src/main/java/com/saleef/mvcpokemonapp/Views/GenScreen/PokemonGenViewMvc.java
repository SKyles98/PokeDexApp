package com.saleef.mvcpokemonapp.Views.GenScreen;

import com.saleef.mvcpokemonapp.Commons.MvcSkeleton.ObservableMvc;
import com.saleef.mvcpokemonapp.ViewModel.Pokemon;

import java.util.List;

public interface PokemonGenViewMvc extends ObservableMvc<PokemonGenViewMvc.Listener>{

    interface Listener{
       void onPokemonClicked(Pokemon pokemon);
    }


    void bindPokemon(List<Pokemon> list);
    void showProgressIndication();
    void hideProgressIndication();

}
