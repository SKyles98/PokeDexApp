package com.saleef.mvcpokemonapp.Networking.Wrappers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PokemonGenerationWrapperSchema {


    @SerializedName("pokemon_species")
    @Expose
    private final List<PokeGenSchema> mPokeGenSchemaList;



    public PokemonGenerationWrapperSchema(List<PokeGenSchema> pokeGenSchemas){
        mPokeGenSchemaList = pokeGenSchemas;
    }


    public List<PokeGenSchema> getPokeGenSchemaList() {
        return mPokeGenSchemaList;
    }
}
