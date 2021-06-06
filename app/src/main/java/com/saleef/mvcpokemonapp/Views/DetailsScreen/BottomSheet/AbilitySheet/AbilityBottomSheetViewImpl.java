package com.saleef.mvcpokemonapp.Views.DetailsScreen.BottomSheet.AbilitySheet;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.saleef.mvcpokemonapp.ViewModel.AbilityDetails;
import com.saleef.mvcpokemonapp.Commons.MvcSkeleton.BaseViewImpl;
import com.saleef.mvcpokemonapp.R;


// View class shows a bottom sheet when a ability button is clicked
public class AbilityBottomSheetViewImpl extends BaseViewImpl implements AbilityBottomSheetViewMvc {

    private final TextView abilityTitle,gameDescriptionTitle
            ,gameDescription,effectDescriptionTitle,effectDescription,inDepthDescriptionTitle,inDepthDescription;
    private final LinearLayout mLinearLayout;
    private final ProgressBar mProgressBar;

    public AbilityBottomSheetViewImpl(LayoutInflater inflater, ViewGroup viewGroup){
         setRootView(inflater.inflate(R.layout.ability_bottom_sheet,viewGroup,false));
         mProgressBar = findViewById(R.id.abilityProgressBar);
         abilityTitle = findViewById(R.id.ablitiyTitleTxt);
         gameDescriptionTitle = findViewById(R.id.gameDescriptionTitleTxt);
         gameDescription = findViewById(R.id.gameDescriptionTxt);
         effectDescriptionTitle = findViewById(R.id.effectDescriptionTitleTxt);
         effectDescription = findViewById(R.id.effectDescriptionTxt);
         inDepthDescriptionTitle = findViewById(R.id.inDepthDescriptionTitleTxt);
         inDepthDescription = findViewById(R.id.inDepthDescriptionTxt);
         mLinearLayout = findViewById(R.id.topBarBackGround);
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
    public void bindAbilityDescriptions(AbilityDetails abilityDetails) {
         bindViews(abilityDetails);
    }

    private void bindViews(AbilityDetails abilityDetails){
        abilityTitle.setText(abilityDetails.getName());
        gameDescription.setText(abilityDetails.getFlavortextAbilityDescription());
        effectDescription.setText(abilityDetails.getShortenedAbilityDescription());
        inDepthDescription.setText(abilityDetails.getCommonAbilityDescription());
        mLinearLayout.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.silver));
    }


}
