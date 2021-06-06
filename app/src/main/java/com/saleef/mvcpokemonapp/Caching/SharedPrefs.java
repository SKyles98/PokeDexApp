package com.saleef.mvcpokemonapp.Caching;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.saleef.mvcpokemonapp.ViewModel.Pokemon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class SharedPrefs {

   private final SharedPreferences mSharedPrefs;

    public SharedPrefs(SharedPreferences sharedPrefs){
        mSharedPrefs = sharedPrefs;
    }


    public void addPokeGen(List<Pokemon> pokemons, int genNumber){
           SharedPreferences.Editor editor = mSharedPrefs.edit();
           // Convert our custom object list to a gson String
        Gson gson = new Gson();
        String gsonConvertedPokemon = gson.toJson(pokemons);
        String key = String.valueOf(genNumber);
        editor.putString(key,gsonConvertedPokemon);
        editor.apply();
    }


    public List<Pokemon> getSharedPrefs(int genNumber){
         String key = String.valueOf(genNumber);
        String savedGen =  mSharedPrefs.getString(key,"");
        if (savedGen==null) {
            return null;
        }

            Gson gson = new Gson();
            Pokemon[] pokemon = gson.fromJson(savedGen, Pokemon[].class);

            int length = pokemon.length;
            List<Pokemon> pokemons = new ArrayList<>(length);
        pokemons.addAll(Arrays.asList(pokemon).subList(0, length));

        return pokemons;
    }


    public boolean checkExistence(int genNumber){
        String key = String.valueOf(genNumber);
       return mSharedPrefs.contains(key);
    }



}
