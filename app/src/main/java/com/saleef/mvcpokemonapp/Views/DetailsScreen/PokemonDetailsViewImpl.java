package com.saleef.mvcpokemonapp.Views.DetailsScreen;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.button.MaterialButton;
import com.saleef.mvcpokemonapp.Commons.MvcSkeleton.BaseObservableImpl;
import com.saleef.mvcpokemonapp.ViewModel.Pokemon;
import com.saleef.mvcpokemonapp.R;



import java.util.List;

public class PokemonDetailsViewImpl extends BaseObservableImpl<PokemonDetailViewMvc.Listener> implements PokemonDetailViewMvc {



        private final ImageView pokeImage;
        private final TextView weightTxt,heightTxt,nameTxt,descriptionTxt;
        private final MaterialButton abilityButton1, abilityButton2,hiddenAbilityButton;
        private final ConstraintLayout mConstraintLayout;
        private final RequestOptions mRequestOptions;
        private final ProgressBar mProgressBar;
        private Pokemon mPokemon;


        public PokemonDetailsViewImpl(LayoutInflater layoutInflater, ViewGroup viewGroup){
            setRootView(layoutInflater.inflate(R.layout.fragment_pokemon_details,viewGroup,false));
            pokeImage = findViewById(R.id.pokemonImage);
            weightTxt = findViewById(R.id.weightText);
            heightTxt = findViewById(R.id.heightText);
            nameTxt = findViewById(R.id.pokeName);
            descriptionTxt = findViewById(R.id.pokeDescriptionTxt);
            mProgressBar = findViewById(R.id.loadProgress);
            mConstraintLayout = findViewById(R.id.pokebackGround);
            abilityButton1 = findViewById(R.id.abilityButton1);
            abilityButton2 = findViewById(R.id.abilityButton2);
            hiddenAbilityButton = findViewById(R.id.hiddenabilityButton3);
            mRequestOptions = new RequestOptions().centerCrop().placeholder(R.drawable.ic_launcher_background).
                    error(R.drawable.ic_launcher_background);
            setClickListeners();
        }


        private void setClickListeners(){
            abilityButton1.setOnClickListener(v -> {
                   for (Listener listener:getListenerTypes()){
                       listener.onAbilityButtonClicked(mPokemon.getAbilitesUrls().get(0));
                   }
            });
            abilityButton2.setOnClickListener(v -> {
                for (Listener listener:getListenerTypes()){
                    listener.onAbilityButtonClicked(mPokemon.getAbilitesUrls().get(1));
                }
            });
            hiddenAbilityButton.setOnClickListener(v -> {
                for (Listener listener:getListenerTypes()){
                    listener.onAbilityButtonClicked(mPokemon.getAbilitesUrls().get(2));
                }
            });
        }


    @Override
    public void showProgressIndication() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressIndication() {
       mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void bindPokemonDetails(Pokemon pokemon) {
            bindViews(pokemon);
    }




    private void bindViews(Pokemon pokemon){
            mPokemon = pokemon;
        Glide.with(getContext()).
                load(pokemon.getImage()) // loads image based off a url
               .apply(mRequestOptions). // apply image based off options
                into(pokeImage);
        double kgtoPounds = Math.round((pokemon.getWeight() * (2.2 * 100.0)))/100.0;
        double cmtoFeet = pokemon.getHeight()/10;
        String pounds = String.valueOf(kgtoPounds/10) + "lbs";
        String meters = String.valueOf(cmtoFeet) + "meters";
        weightTxt.setText(pounds);
        heightTxt.setText(meters);
        descriptionTxt.setText(pokemon.getDescription());
        nameTxt.setText(pokemon.getName());
       handleButtons(pokemon.getAbilitiesChange());
       setColors(pokemon.getColor());
    }

    private void handleButtons(List<String> abilities){
        for (int i = 0;i<abilities.size();i++){
            switch (i){
                case 0:
                    abilityButton1.setText(abilities.get(i));
                    break;
                case 1:
                    abilityButton2.setText(abilities.get(i));
                    break;
                case 2:
                    hiddenAbilityButton.setText(abilities.get(i));
                    break;
                default:
                    break;
            }
        }
        if (abilityButton2.getText().toString().isEmpty()){
            hiddenAbilityButton.setVisibility(View.GONE);
            abilityButton2.setVisibility(View.GONE);
        } else{
            hiddenAbilityButton.setVisibility(View.VISIBLE);
            abilityButton2.setVisibility(View.VISIBLE);
        }
        if (hiddenAbilityButton.getText().toString().isEmpty()){
            hiddenAbilityButton.setVisibility(View.GONE);
        } else{
            hiddenAbilityButton.setVisibility(View.VISIBLE);
        }
    }

    private void setColors(String color){
            switch(color){
                case "green":
                    mConstraintLayout.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.light_green));
                    abilityButton1.getBackground().setTint(ContextCompat.getColor(getContext(),R.color.light_green));
                    abilityButton2.getBackground().setTint(ContextCompat.getColor(getContext(),R.color.light_green));
                    hiddenAbilityButton.getBackground().setTint(ContextCompat.getColor(getContext(),R.color.light_green));
                    break;
                case "purple":
                    mConstraintLayout.setBackgroundColor(getRootView().getResources().getColor(R.color.dark_purple));
                    abilityButton1.getBackground().setTint(ContextCompat.getColor(getContext(),R.color.dark_purple));
                    abilityButton2.getBackground().setTint(ContextCompat.getColor(getContext(),R.color.dark_purple));
                    hiddenAbilityButton.getBackground().setTint(ContextCompat.getColor(getContext(),R.color.dark_purple));
                    break;
                case "gray":
                    mConstraintLayout.setBackgroundColor(getRootView().getResources().getColor(R.color.primary_grey));
                    abilityButton1.getBackground().setTint(ContextCompat.getColor(getContext(),R.color.primary_grey));
                    abilityButton2.getBackground().setTint(ContextCompat.getColor(getContext(),R.color.primary_grey));
                    hiddenAbilityButton.getBackground().setTint(ContextCompat.getColor(getContext(),R.color.primary_grey));
                    break;
                case "blue":
                    mConstraintLayout.setBackgroundColor(getRootView().getResources().getColor(R.color.sky_blue));
                    abilityButton1.getBackground().setTint(ContextCompat.getColor(getContext(),R.color.sky_blue));
                    abilityButton2.getBackground().setTint(ContextCompat.getColor(getContext(),R.color.sky_blue));
                    hiddenAbilityButton.getBackground().setTint(ContextCompat.getColor(getContext(),R.color.sky_blue));
                    break;
                case "yellow":
                    mConstraintLayout.setBackgroundColor(getRootView().getResources().getColor(R.color.yellow));
                    abilityButton1.getBackground().setTint(ContextCompat.getColor(getContext(),R.color.yellow));
                    abilityButton2.getBackground().setTint(ContextCompat.getColor(getContext(),R.color.yellow));
                    hiddenAbilityButton.getBackground().setTint(ContextCompat.getColor(getContext(),R.color.yellow));
                    break;
                case "red":
                    mConstraintLayout.setBackgroundColor(getRootView().getResources().getColor(R.color.red));
                    abilityButton1.getBackground().setTint(ContextCompat.getColor(getContext(),R.color.red));
                    abilityButton2.getBackground().setTint(ContextCompat.getColor(getContext(),R.color.red));
                    hiddenAbilityButton.getBackground().setTint(ContextCompat.getColor(getContext(),R.color.red));
                    break;
                case "white":
                    mConstraintLayout.setBackgroundColor(getRootView().getResources().getColor(R.color.silver));
                    abilityButton1.getBackground().setTint(ContextCompat.getColor(getContext(),R.color.silver));
                    abilityButton2.getBackground().setTint(ContextCompat.getColor(getContext(),R.color.silver));
                    hiddenAbilityButton.getBackground().setTint(ContextCompat.getColor(getContext(),R.color.silver));
                    break;
                case "pink":
                    mConstraintLayout.setBackgroundColor(getRootView().getResources().getColor(R.color.light_pink));
                    abilityButton1.getBackground().setTint(ContextCompat.getColor(getContext(),R.color.light_pink));
                    abilityButton2.getBackground().setTint(ContextCompat.getColor(getContext(),R.color.light_pink));
                    hiddenAbilityButton.getBackground().setTint(ContextCompat.getColor(getContext(),R.color.light_pink));
                    break;
                case "brown":
                    mConstraintLayout.setBackgroundColor(getRootView().getResources().getColor(R.color.bronze));
                    abilityButton1.getBackground().setTint(ContextCompat.getColor(getContext(),R.color.bronze));
                    abilityButton2.getBackground().setTint(ContextCompat.getColor(getContext(),R.color.bronze));
                    hiddenAbilityButton.getBackground().setTint(ContextCompat.getColor(getContext(),R.color.bronze));
                    break;
                default:
                    mConstraintLayout.setBackgroundColor(getRootView().getResources().getColor(R.color.white));
                    abilityButton1.getBackground().setTint(ContextCompat.getColor(getContext(),R.color.white));
                    abilityButton2.getBackground().setTint(ContextCompat.getColor(getContext(),R.color.white));
                    hiddenAbilityButton.getBackground().setTint(ContextCompat.getColor(getContext(),R.color.white));
                    break;
            }

    }
}
