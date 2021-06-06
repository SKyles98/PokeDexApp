package com.saleef.mvcpokemonapp.Networking.Wrappers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.saleef.mvcpokemonapp.Networking.Model.AbilitiesSchema;
import com.saleef.mvcpokemonapp.Networking.Model.SpritesSchema;
import com.saleef.mvcpokemonapp.Networking.Model.PokemonType;
import com.saleef.mvcpokemonapp.Networking.Model.Species;

import java.util.List;

public class PokemonDetailWrapperSchema {

    @SerializedName("abilities")
    @Expose
    private final List<AbilitiesSchema> mAbilitiesSchemas;


    @SerializedName("species")
    @Expose
    private final Species mSpecies;

    @SerializedName("name")
    @Expose
    private final String name;

    @SerializedName("order")
    @Expose
    private final int pokedexNumber;

    @SerializedName("id")
    @Expose
    private final int pokedexId;
    @SerializedName("height")
    @Expose
    private final int height;

    @SerializedName("types")
    @Expose
    private final List<PokemonType> mPokemonTypes;
    @SerializedName("weight")
    @Expose
    private final int weight;

    @SerializedName("sprites")
    private final SpritesSchema mSpritesSchema;
    public PokemonDetailWrapperSchema(List<AbilitiesSchema> abilitiesSchemas,Species species,
                                      String name,int pokedexNumber,int height,int weight,
                                      SpritesSchema spritesSchema,List<PokemonType> pokemonTypes,int pokedexId){
        mAbilitiesSchemas = abilitiesSchemas;
        mSpecies = species;
        this.name = name;
        this.pokedexNumber = pokedexNumber;
        this.height = height;
        this.weight = weight;
        mPokemonTypes = pokemonTypes;
        mSpritesSchema = spritesSchema;
        this.pokedexId = pokedexId;
    }


    public int getPokedexId() {
        return pokedexId;
    }

    public List<AbilitiesSchema> getAbilitiesSchemas() {
        return mAbilitiesSchemas;
    }

    public SpritesSchema getSpritesSchema() {
        return mSpritesSchema;
    }

    public Species getSpecies() {
        return mSpecies;
    }

    public String getName() {
        return name;
    }

    public int getPokedexNumber() {
        return pokedexNumber;
    }

    public int getHeight() {
        return height;
    }

    public List<PokemonType> getPokemonTypes() {
        return mPokemonTypes;
    }

    public int getWeight() {
        return weight;
    }


   public PokemonDetailWrapperSchema clone() throws CloneNotSupportedException {

       PokemonDetailWrapperSchema clone = (PokemonDetailWrapperSchema) super.clone();
       return new PokemonDetailWrapperSchema(getAbilitiesSchemas(),getSpecies(),getName(),
                getPokedexNumber(),getHeight(),getWeight(),getSpritesSchema(),getPokemonTypes(),getPokedexId());
   }
}


