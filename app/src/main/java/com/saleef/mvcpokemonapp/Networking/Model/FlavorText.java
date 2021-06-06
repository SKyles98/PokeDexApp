package com.saleef.mvcpokemonapp.Networking.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FlavorText {



    @SerializedName("flavor_text")
    @Expose
    private String pokemonDescription;

    @SerializedName("language")
    @Expose
    private Language mLanguage;

    public String getPokemonDescription() {
        return pokemonDescription;
    }

    public Language getLanguage() {
        return mLanguage;
    }
}
