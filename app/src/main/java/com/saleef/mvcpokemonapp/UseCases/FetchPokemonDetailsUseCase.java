package com.saleef.mvcpokemonapp.UseCases;

import com.saleef.mvcpokemonapp.Commons.MvcSkeleton.BaseObservableImpl;
import com.saleef.mvcpokemonapp.Networking.API.PokemonApi;

import com.saleef.mvcpokemonapp.Networking.Model.FlavorText;
import com.saleef.mvcpokemonapp.Networking.Wrappers.PokemonSpeciesInfoWrapperSchema;
import com.saleef.mvcpokemonapp.ViewModel.Pokemon;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;

import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FetchPokemonDetailsUseCase extends BaseObservableImpl<FetchPokemonDetailsUseCase.Listener> {

   public interface Listener{
       void onRetrievalSuccess(Pokemon pokemon);
       void onRetrievalFailure(String errorMessage);
    }

    private final PokemonApi mPokemonApi;
    public FetchPokemonDetailsUseCase(PokemonApi pokemonApi){
        mPokemonApi = pokemonApi;
    }




    public void fetchPokemonDetails(Pokemon pokemon){
        // We already have height weight,name,types,abilites
        // what we need is description color , maybe baseStats for later
       Pokemon temp = pokemon;
        Observable<PokemonSpeciesInfoWrapperSchema> call = mPokemonApi.getPokemonSpecies(pokemon.getId());

        call.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).map(new Function<PokemonSpeciesInfoWrapperSchema, Pokemon>() {
            @Override
            public Pokemon apply(PokemonSpeciesInfoWrapperSchema pokemonSpeciesInfoWrapperSchema) throws Throwable {

              for (FlavorText flavorText:pokemonSpeciesInfoWrapperSchema.getFlavorTextList()){
                  if (flavorText.getLanguage().getName().equalsIgnoreCase("en")){
                      temp.setDescription(flavorText.getPokemonDescription());
                      break;
                  }
              }

              temp.setColor(pokemonSpeciesInfoWrapperSchema.getPokeColor().getPokemonColor());
              return temp;
            }
        }).subscribe(this::onSuccess,this::onFailure);

    }


    private void onSuccess(Pokemon pokemon){
          for(Listener listener:getListenerTypes()){
              listener.onRetrievalSuccess(pokemon);
          }
    }


    private void onFailure(Throwable t){
        for(Listener listener:getListenerTypes()){
            listener.onRetrievalFailure(t.toString());
        }
    }
}
