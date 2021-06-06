package com.saleef.mvcpokemonapp.UseCases;

import com.saleef.mvcpokemonapp.ViewModel.AbilityDetails;
import com.saleef.mvcpokemonapp.Commons.MvcSkeleton.BaseObservableImpl;
import com.saleef.mvcpokemonapp.Networking.API.PokemonApi;
import com.saleef.mvcpokemonapp.Networking.Model.EffectEntries;
import com.saleef.mvcpokemonapp.Networking.Model.FlavorText;
import com.saleef.mvcpokemonapp.Networking.Wrappers.AbilityWrapperSchema;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

// This use fetches the details of a pokemons ability while the use is in the detail page
public class FetchAbilityDetailUseCase extends BaseObservableImpl<FetchAbilityDetailUseCase.Listener> {

    private final PokemonApi mPokemonApi;


   public interface Listener{
        void onRetrievalSuccess(AbilityDetails abilityDetails);
        void onRetrievalFailure(String errorMessage);
    }


    public FetchAbilityDetailUseCase(PokemonApi pokemonApi){
        mPokemonApi = pokemonApi;
    }


    public void FetchAbilityDetails(String abilityUrl){

        Observable<AbilityWrapperSchema> observable = mPokemonApi.getAbilityDetails(abilityUrl);
        // Lambda basically does the apply method and returns an ability schema
        observable.map(abilityWrapperSchema -> abilityWrapperSchema)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(this::onSuccess,this::onFailure);
    }



    private void onSuccess(AbilityWrapperSchema abilityWrapperSchema){
        //Common Effect,Shortened Effect,FlavorEffect
        List<String> abilityEffects = new ArrayList<>(3);

        for (EffectEntries entries:abilityWrapperSchema.getEffectEntriesList()){
            if (entries.getLanguage().getName().equals("en")){
                abilityEffects.add(entries.getEffect());
                abilityEffects.add(entries.getShortenedEffect());
                break;
            }
        }
        for (FlavorText flavorText:abilityWrapperSchema.getFlavorTexts()){
             if (flavorText.getLanguage().getName().equals("en")){
                abilityEffects.add(flavorText.getPokemonDescription());
                break;
             }
        }
        String name = abilityWrapperSchema.getMname();
        AbilityDetails abilityDetails = new AbilityDetails(abilityEffects.get(0),abilityEffects.get(1),abilityEffects.get(2),name);
        for (Listener listener:getListenerTypes()){
            listener.onRetrievalSuccess(abilityDetails);
        }

    }


    private void onFailure(Throwable t){
        for (Listener listener:getListenerTypes()){
            listener.onRetrievalFailure(t.toString());
        }
    }
}
