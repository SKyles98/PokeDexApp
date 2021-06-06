package com.saleef.mvcpokemonapp.Networking.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EffectEntries {



    @SerializedName("effect")
    @Expose
    private String effect;


    @SerializedName("short_effect")
    @Expose
    private String shortenedEffect;

    @SerializedName("language")
    @Expose
    private Language mLanguage;


    public Language getLanguage() {
        return mLanguage;
    }

    public String getEffect() {
        return effect;
    }

    public String getShortenedEffect() {
        return shortenedEffect;
    }
}
