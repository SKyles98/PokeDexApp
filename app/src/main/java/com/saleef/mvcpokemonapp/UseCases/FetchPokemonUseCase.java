package com.saleef.mvcpokemonapp.UseCases;

import android.util.Log;

import com.saleef.mvcpokemonapp.Caching.SharedPrefs;
import com.saleef.mvcpokemonapp.Commons.MvcSkeleton.BaseObservableImpl;
import com.saleef.mvcpokemonapp.Networking.Wrappers.PokeGenSchema;
import com.saleef.mvcpokemonapp.Networking.API.PokemonApi;
import com.saleef.mvcpokemonapp.Networking.Wrappers.PokemonDetailWrapperSchema;
import com.saleef.mvcpokemonapp.Networking.Wrappers.PokemonGenerationWrapperSchema;
import com.saleef.mvcpokemonapp.ViewModel.Pokemon;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FetchPokemonUseCase extends BaseObservableImpl<FetchPokemonUseCase.Listener> {

    private final PokemonApi mPokemonApi;
    private final SharedPrefs mSharedPrefs;
    private int testGen;
    public interface Listener{
        void onCacheRetrievalSuccess(List<Pokemon> pokemonList);
        void onRetrievalSuccess(List<Pokemon> pokemonList);
        void onRetrievalFailure(String errorMessage);
        void addDisposable(Disposable disposable);
    }


    public FetchPokemonUseCase(PokemonApi pokemonApi,SharedPrefs sharedPrefs){
        mPokemonApi = pokemonApi;
        mSharedPrefs = sharedPrefs;
    }


    private int getTestGen(){
        return testGen;
    }
    public void fetchPokemonInfo(int genNumber){
        testGen = genNumber;

        // If we already performed the network call then the list should already be in prefs thus we load from cache instead which should be much faster
        if (!mSharedPrefs.checkExistence(genNumber)) {
            Observable<PokemonGenerationWrapperSchema> genObservable = mPokemonApi.getPokemonGenerations(genNumber);
            Log.i("gen", String.valueOf(genNumber));
            // Make a request to the api to get pokemon names from the corresponding generation
            genObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .map(new Function<PokemonGenerationWrapperSchema, List<Integer>>() {
                        @Override
                        public List<Integer> apply(PokemonGenerationWrapperSchema pokemonGenerationWrapperSchema) throws Throwable {
                            // Get the names from the result
                            List<PokeGenSchema> pokeGenSchemaList = pokemonGenerationWrapperSchema.getPokeGenSchemaList();
                            List<String> pokeNames = new ArrayList<>(pokeGenSchemaList.size());
                            List<Integer> ids = new ArrayList<>(pokeGenSchemaList.size());
                            for (PokeGenSchema pokeGenSchema : pokeGenSchemaList) {
                                pokeNames.add(pokeGenSchema.getPokemonName());
                                int id = Integer.parseInt(pokeGenSchema.getSpeciesUrl().substring(42, pokeGenSchema.getSpeciesUrl().lastIndexOf("/")));
                                ids.add(id);
                            }

                            return ids;
                        }
                    }).subscribe(this::fetchPokemonSpeciesInfo, this::onFetchFailure);
        } else{
            loadCache();
        }

    }


    private void fetchPokemonSpeciesInfo(List<Integer> ids) {
            List<Observable<PokemonDetailWrapperSchema>> requests = new ArrayList<>(ids.size());
        for (int pokeid:ids){
                requests.add(mPokemonApi.getPokemonDetails(pokeid));
        }


        Observable.zip(requests, new Function<Object[], List<PokemonDetailWrapperSchema>>() {
            @Override
            public List<PokemonDetailWrapperSchema> apply(Object[] objects) throws Throwable {
                // Objects is an array of all the requests in this case pokemon detail calls;
                List<PokemonDetailWrapperSchema> pokemonDetailWrapperSchemas = new ArrayList<>(objects.length);
                for (Object object:objects){
                   pokemonDetailWrapperSchemas.add((PokemonDetailWrapperSchema) object);
                }
                return pokemonDetailWrapperSchemas;
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<List<PokemonDetailWrapperSchema>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                        sendDisposbale(d);
            }

            @Override
            public void onNext(@NonNull List<PokemonDetailWrapperSchema> list) {
                       extractPokemon(list);
            }

            @Override
            public void onError(@NonNull Throwable e) {

                e.printStackTrace();
                     onFetchFailure(e);
            }

            @Override
            public void onComplete() {
                     //Yay
            }
        });


    }

    private void sendDisposbale(Disposable d) {
        for (Listener listener:getListenerTypes()){
            listener.addDisposable(d);
        }
    }


    private void loadCache(){
        for (Listener listener:getListenerTypes()){
            listener.onCacheRetrievalSuccess(mSharedPrefs.getSharedPrefs(getTestGen()));
        }
    }

    private void extractPokemon(List<PokemonDetailWrapperSchema> list){
        List<Pokemon> pokemons = new ArrayList<>(list.size());

        for (PokemonDetailWrapperSchema pokemonDetailWrapperSchema:list){
            pokemons.add(new Pokemon(pokemonDetailWrapperSchema.getPokedexId(),
                    pokemonDetailWrapperSchema.getHeight(),pokemonDetailWrapperSchema.getWeight()
                    ,pokemonDetailWrapperSchema.getPokemonTypes(),
                    pokemonDetailWrapperSchema.getSpritesSchema().getOther().getOfficialArtwork().getPokeImage()
                    ,pokemonDetailWrapperSchema.getAbilitiesSchemas(),pokemonDetailWrapperSchema.getName()));
        }
        // After the long network call we store the list inside the prefs
        mSharedPrefs.addPokeGen(pokemons,getTestGen());
        for (Listener listener:getListenerTypes()){
            listener.onRetrievalSuccess(pokemons);
        }

   }

    private void onFetchFailure(Throwable t){
        for (Listener listener:getListenerTypes()){
            listener.onRetrievalFailure(t.toString());
        }
    }
}
