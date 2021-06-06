package com.saleef.mvcpokemonapp.Views.GenScreen;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.saleef.mvcpokemonapp.Commons.Bases.BaseFragment;
import com.saleef.mvcpokemonapp.UseCases.FetchPokemonUseCase;
import com.saleef.mvcpokemonapp.ViewModel.Pokemon;
import com.saleef.mvcpokemonapp.Commons.ScreenNavigator.ScreenNavigaton;

import java.util.List;

import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;


public class PokedexGenFragment extends BaseFragment implements FetchPokemonUseCase.Listener, PokemonGenViewMvc.Listener {

   private static final String GEN_NUMBER = "GENNUMBER";
   private FetchPokemonUseCase mFetchPokemonUseCase;
   private PokemonGenViewMvc mPokemonGenViewMvc;
   private CompositeDisposable mCompositeDisposable;
   private ScreenNavigaton mScreenNavigaton;

    public PokedexGenFragment() {

    }


    public static PokedexGenFragment newInstance(int genNumber) {
        PokedexGenFragment fragment = new PokedexGenFragment();
        Bundle args = new Bundle();
       args.putInt(GEN_NUMBER,genNumber);
        fragment.setArguments(args);
        return fragment;
    }


    private int getPassedGen(){
        return getArguments().getInt(GEN_NUMBER);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
             mFetchPokemonUseCase = getControllerCompositionRoot().getFetchPokemonUseCase();
             mCompositeDisposable = new CompositeDisposable();
             mScreenNavigaton = getControllerCompositionRoot().getScreeNavigation();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mPokemonGenViewMvc = getControllerCompositionRoot().getViewMvcFactory().getPokemonGenViewImpl(container);
        return mPokemonGenViewMvc.getRootView();
    }
    @Override
    public void onStart() {
        super.onStart();
        mFetchPokemonUseCase.register(this);
        mPokemonGenViewMvc.register(this);
        fetchGens();
    }

    @Override
    public void onStop() {
        super.onStop();
        mFetchPokemonUseCase.unregister(this);
        mPokemonGenViewMvc.unregister(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mCompositeDisposable.clear();
    }

    private void fetchGens(){
        mPokemonGenViewMvc.showProgressIndication();
        mFetchPokemonUseCase.fetchPokemonInfo(getPassedGen());
    }

    @Override
    public void onCacheRetrievalSuccess(List<Pokemon> pokemonList) {
        mPokemonGenViewMvc.hideProgressIndication();
        mPokemonGenViewMvc.bindPokemon(pokemonList);
    }

    @Override
    public void onRetrievalSuccess(List<Pokemon> pokemonList) {
       mPokemonGenViewMvc.hideProgressIndication();
       mPokemonGenViewMvc.bindPokemon(pokemonList);
    }

    @Override
    public void onRetrievalFailure(String errorMessage) {
        mPokemonGenViewMvc.hideProgressIndication();
        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void addDisposable(Disposable disposable) {
        if (mCompositeDisposable.size() > 2){
            mCompositeDisposable.clear();
        }
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void onPokemonClicked(Pokemon pokemon) {
        //Navigate to detail fragment screen
         mScreenNavigaton.navigateToPokemonDetails(pokemon);
    }


}