package com.saleef.mvcpokemonapp.Networking.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PokeColor {

    @SerializedName("name")
    @Expose
    private String pokemonColor;


    public String getPokemonColor() {
        return pokemonColor;
    }
}
