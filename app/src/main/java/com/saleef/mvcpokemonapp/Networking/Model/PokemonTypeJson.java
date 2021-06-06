package com.saleef.mvcpokemonapp.Networking.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PokemonTypeJson {



    @SerializedName("name")
    @Expose
    private  String typeName;


    public String getTypeName() {
        return typeName;
    }
}
