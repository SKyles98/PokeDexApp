package com.saleef.mvcpokemonapp.Views.GenScreen.Adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.saleef.mvcpokemonapp.Views.GenScreen.ViewHolder.PokeDexViewHolderMvc;
import com.saleef.mvcpokemonapp.Views.GenScreen.ViewHolder.PokedexViewHolderImpl;
import com.saleef.mvcpokemonapp.ViewModel.Pokemon;
import com.saleef.mvcpokemonapp.Commons.ViewFactory.ViewMvcFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PokedexRecyclerAdapter extends RecyclerView.Adapter<PokedexRecyclerAdapter.PokedexViewHolder>  implements PokeDexViewHolderMvc.Listener {

     public interface Listener{
        void onPokemonClicked(Pokemon pokemon);
      }

    private ArrayList<Pokemon> mPokemonArrayList;
    private final Listener mListener;
    private final ViewMvcFactory mViewMvcFactory;

    public PokedexRecyclerAdapter(Listener listener,ViewMvcFactory viewMvcFactory){
        mPokemonArrayList = new ArrayList<>();
        mListener = listener;
        mViewMvcFactory = viewMvcFactory;
    }
    @NonNull
    @Override
    public PokedexViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       PokedexViewHolderImpl pokedexViewHolder =  mViewMvcFactory.getPokedexViewHolderImpl(parent);
       pokedexViewHolder.register(this);
        return new PokedexViewHolder(pokedexViewHolder);
    }

    @Override
    public void onBindViewHolder(@NonNull PokedexViewHolder holder, int position) {
            holder.mPokedex.bindPokemon(mPokemonArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return mPokemonArrayList.size();
    }

    public void bindPokemon(List<Pokemon> pokemons){
        Collections.sort(pokemons,new Pokemon());
        mPokemonArrayList = (ArrayList<Pokemon>) pokemons;
        notifyDataSetChanged();
    }

    @Override
    public void onPokemonClicked(Pokemon pokemon) {
            mListener.onPokemonClicked(pokemon);
    }

    static class PokedexViewHolder extends RecyclerView.ViewHolder{
         private  final PokedexViewHolderImpl mPokedex;
        public PokedexViewHolder(@NonNull PokedexViewHolderImpl pokedexViewHolder)
        {
            super(pokedexViewHolder.getRootView());
            mPokedex = pokedexViewHolder;
        }
    }
}
