package com.saleef.mvcpokemonapp.Networking.Wrappers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.saleef.mvcpokemonapp.Networking.Model.EffectEntries;
import com.saleef.mvcpokemonapp.Networking.Model.FlavorText;

import java.util.List;

public class AbilityWrapperSchema {


    @SerializedName("effect_entries")
    @Expose
    private final List<EffectEntries> mEffectEntriesList;

    @SerializedName("flavor_text_entries")
    @Expose
    private final List<FlavorText> mFlavorTexts;

    @SerializedName("name")
    @Expose
    private final String mname;
       public AbilityWrapperSchema(List<EffectEntries> effectEntriesList, List<FlavorText> flavorTexts,String name){
           mEffectEntriesList = effectEntriesList;
           mFlavorTexts = flavorTexts;
           mname = name;
       }

    public String getMname() {
        return mname;
    }

    public List<EffectEntries> getEffectEntriesList() {
        return mEffectEntriesList;
    }

    public List<FlavorText> getFlavorTexts() {
        return mFlavorTexts;
    }
}
