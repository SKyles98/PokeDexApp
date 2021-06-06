package com.saleef.mvcpokemonapp.Networking.API;



import com.saleef.mvcpokemonapp.Networking.Wrappers.AbilityWrapperSchema;
import com.saleef.mvcpokemonapp.Networking.Wrappers.PokemonDetailWrapperSchema;
import com.saleef.mvcpokemonapp.Networking.Wrappers.PokemonGenerationWrapperSchema;
import com.saleef.mvcpokemonapp.Networking.Wrappers.PokemonSpeciesInfoWrapperSchema;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

// Brainstorming
/*
1st step is to get all pokemon present in the given generation. User clicks on a tab named the gen number and we load all pokemon
from the generation based off that number So in practice we will call get pokemonGenerations which gets our names of pokemon in the generation
and then we will call getPokemon details based off those names to load image,type,dexnumber,name of pokemon
 */
public interface PokemonApi {

    @GET("pokemon/{id}/") // Gets our pokemon details when we provide pokemon name or id
    Observable<PokemonDetailWrapperSchema> getPokemonDetails(@Path("id") int id);

    @GET("generation/{gen}/")
    Observable<PokemonGenerationWrapperSchema> getPokemonGenerations(@Path("gen") int genNumber);

     // Gets the species info of a pokemon will typically be called after get pokemon details when we are loading details
    @GET("pokemon-species/{id}/")
    Observable<PokemonSpeciesInfoWrapperSchema> getPokemonSpecies(@Path("id") int id);

    @GET
    Observable<AbilityWrapperSchema> getAbilityDetails(@Url String url);
}
