package com.saleef.mvcpokemonapp.Networking.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PokemonType {

    @SerializedName("type")
    @Expose
    private PokemonTypeJson mPokemonTypeJson;


    public PokemonTypeJson getPokemonTypeJson() {
        return mPokemonTypeJson;
    }




}
