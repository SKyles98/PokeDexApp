package com.saleef.mvcpokemonapp.Networking.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AbilitiesSchema {
    @SerializedName("ability")
    @Expose
    private AbilityJson mAbilityJson;


    public AbilityJson getAbilityJson() {
        return mAbilityJson;
    }
}
