package com.saleef.mvcpokemonapp.Views.DetailsScreen;

import android.os.Bundle;

import androidx.annotation.NonNull;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.saleef.mvcpokemonapp.Commons.Bases.BaseFragment;
import com.saleef.mvcpokemonapp.ViewModel.Pokemon;
import com.saleef.mvcpokemonapp.Commons.ScreenNavigator.ScreenNavigaton;
import com.saleef.mvcpokemonapp.UseCases.FetchPokemonDetailsUseCase;


public class PokemonDetailsFragment extends BaseFragment implements FetchPokemonDetailsUseCase.Listener, PokemonDetailViewMvc.Listener {

  private static final String PICKACHU = "POKEMON_KEY";

  private FetchPokemonDetailsUseCase mFetchPokemonDetailsUseCase;
  private PokemonDetailViewMvc mPokemonDetailViewMvc;
  private ScreenNavigaton mScreenNavigaton;

    public PokemonDetailsFragment() {

    }


    public static PokemonDetailsFragment newInstance(Pokemon pokemon) {
        PokemonDetailsFragment fragment = new PokemonDetailsFragment();
        Bundle args = new Bundle();
        args.putParcelable(PICKACHU,pokemon);
        fragment.setArguments(args);
        return fragment;
    }



    private Pokemon getClickedPokemon(){
       return  getArguments().getParcelable(PICKACHU);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
           mFetchPokemonDetailsUseCase = getControllerCompositionRoot().getFetchPokemonDetailsUseCase();
           mScreenNavigaton = getControllerCompositionRoot().getScreeNavigation();
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mPokemonDetailViewMvc = getControllerCompositionRoot().getViewMvcFactory().getPokemonDetailsViewImpl(container);
        return mPokemonDetailViewMvc.getRootView();
    }

    @Override
    public void onStart() {
        super.onStart();
        mFetchPokemonDetailsUseCase.register(this);
        mPokemonDetailViewMvc.register(this);
        fetchPokemonDetailInfo();
    }

    @Override
    public void onStop() {
        super.onStop();
        mFetchPokemonDetailsUseCase.unregister(this);
        mPokemonDetailViewMvc.unregister(this);
    }

    private void fetchPokemonDetailInfo(){
        mPokemonDetailViewMvc.showProgressIndication();
        mFetchPokemonDetailsUseCase.fetchPokemonDetails(getClickedPokemon());
    }
    @Override
    public void onRetrievalSuccess(Pokemon pokemon) {
        mPokemonDetailViewMvc.hideProgressIndication();
         mPokemonDetailViewMvc.bindPokemonDetails(pokemon);
    }

    @Override
    public void onRetrievalFailure(String errorMessage) {
        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAbilityButtonClicked(String abilityUrl) {
        // Pull up bottom sheet to view information about ability
           mScreenNavigaton.OpenAbilityBottomSheet(abilityUrl);
    }


}