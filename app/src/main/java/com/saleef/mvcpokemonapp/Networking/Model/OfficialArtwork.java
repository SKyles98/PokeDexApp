package com.saleef.mvcpokemonapp.Networking.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OfficialArtwork {

    @SerializedName("front_default")
    @Expose
    private String pokeImage;


    public String getPokeImage() {
        return pokeImage;
    }
}
