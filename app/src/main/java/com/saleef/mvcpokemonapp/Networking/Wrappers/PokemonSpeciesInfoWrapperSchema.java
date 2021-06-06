package com.saleef.mvcpokemonapp.Networking.Wrappers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.saleef.mvcpokemonapp.Networking.Model.FlavorText;
import com.saleef.mvcpokemonapp.Networking.Model.PokeColor;

import java.util.List;

public class PokemonSpeciesInfoWrapperSchema {

    @SerializedName("color")
    @Expose
    private final PokeColor mPokeColor;

    @SerializedName("flavor_text_entries")
    private final List<FlavorText> mFlavorTextList;



    public PokemonSpeciesInfoWrapperSchema(PokeColor pokeColor,List<FlavorText> flavorTextList){
        mPokeColor = pokeColor;
        mFlavorTextList = flavorTextList;
    }


    public List<FlavorText> getFlavorTextList() {
        return mFlavorTextList;
    }

    public PokeColor getPokeColor() {
        return mPokeColor;
    }
}
