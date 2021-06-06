package com.saleef.mvcpokemonapp.Views.DetailsScreen.BottomSheet.AbilitySheet;

import com.saleef.mvcpokemonapp.ViewModel.AbilityDetails;

public interface AbilityBottomSheetViewMvc  {


    void showProgressIndication();
    void hideProgressIndication();
    void bindAbilityDescriptions(AbilityDetails abilityDetails);
}
