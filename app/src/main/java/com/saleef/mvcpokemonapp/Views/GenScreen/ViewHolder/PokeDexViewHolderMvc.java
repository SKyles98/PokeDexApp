package com.saleef.mvcpokemonapp.Views.GenScreen.ViewHolder;

import com.saleef.mvcpokemonapp.ViewModel.Pokemon;

public interface PokeDexViewHolderMvc {

    interface Listener{
        void onPokemonClicked(Pokemon pokemon);
    }


    void bindPokemon(Pokemon pokemon);
}
