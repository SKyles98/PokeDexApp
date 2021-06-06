package com.saleef.mvcpokemonapp.Networking.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AbilityJson {

    @SerializedName("name")
    @Expose
    private String abilityName;

    @SerializedName("url")
    @Expose
    private String abilityUrl;


    public String getAbilityName() {
        return abilityName;
    }

    public String getAbilityUrl() {
        return abilityUrl;
    }
}
