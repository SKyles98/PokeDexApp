package com.saleef.mvcpokemonapp.Networking.Wrappers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PokeGenSchema {


    @SerializedName("name")
    @Expose
    private String pokemonName;

    @SerializedName("url")
    @Expose
    private String speciesUrl;


    public String getSpeciesUrl() {
        return speciesUrl;
    }


    public String getPokemonName() {
        return pokemonName;
    }
}
