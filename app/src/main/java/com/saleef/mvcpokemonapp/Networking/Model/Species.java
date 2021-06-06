package com.saleef.mvcpokemonapp.Networking.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Species {

    @SerializedName("url")
    @Expose
    private String speciesUrl;


    public String getSpeciesUrl() {
        return speciesUrl;
    }
}
