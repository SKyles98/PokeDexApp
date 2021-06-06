package com.saleef.mvcpokemonapp.Networking.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SpritesSchema {



    @SerializedName("other")
    @Expose
    private Other other;


    public Other getOther() {
        return other;
    }


}
