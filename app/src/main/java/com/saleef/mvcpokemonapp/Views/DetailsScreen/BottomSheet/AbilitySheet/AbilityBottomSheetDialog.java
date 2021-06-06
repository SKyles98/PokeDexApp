package com.saleef.mvcpokemonapp.Views.DetailsScreen.BottomSheet.AbilitySheet;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.saleef.mvcpokemonapp.ViewModel.AbilityDetails;
import com.saleef.mvcpokemonapp.Commons.Bases.BaseBottomSheetDialogFragment;
import com.saleef.mvcpokemonapp.UseCases.FetchAbilityDetailUseCase;


// DialogFragment Controller for our bottom sheet
public class AbilityBottomSheetDialog extends BaseBottomSheetDialogFragment implements FetchAbilityDetailUseCase.Listener {

    private static String ABILITY = "ABILITY_KEY";
    private FetchAbilityDetailUseCase mFetchAbilityDetailUseCase;
    private AbilityBottomSheetViewImpl mAbilityBottomSheetView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
          mAbilityBottomSheetView = getControllerCompositionRoot().getViewMvcFactory().getAbilityBottomSheetViewImpl(container);
        return mAbilityBottomSheetView.getRootView();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments()!=null){
            mFetchAbilityDetailUseCase = getControllerCompositionRoot().getFetchAbilityDetailsUseCase();
        }
    }

    public static AbilityBottomSheetDialog newInstance(String abilityUrl) {
        Bundle args = new Bundle();
        AbilityBottomSheetDialog fragment = new AbilityBottomSheetDialog();
        args.putString(ABILITY,abilityUrl);
        fragment.setArguments(args);
        return fragment;
    }

    private String getabilityURl(){
        return getArguments().getString(ABILITY);
    }

    @Override
    public void onStart() {
        super.onStart();
        mFetchAbilityDetailUseCase.register(this);
        fetchAbilityInfo();
    }

    @Override
    public void onStop() {
        super.onStop();
        mFetchAbilityDetailUseCase.unregister(this);
    }

    private void fetchAbilityInfo(){
        mAbilityBottomSheetView.showProgressIndication();
        mFetchAbilityDetailUseCase.FetchAbilityDetails(getabilityURl());
    }
    @Override
    public void onRetrievalSuccess(AbilityDetails abilityDetails) {
        mAbilityBottomSheetView.hideProgressIndication();
               mAbilityBottomSheetView.bindAbilityDescriptions(abilityDetails);
    }

    @Override
    public void onRetrievalFailure(String errorMessage) {
        mAbilityBottomSheetView.hideProgressIndication();
        Toast.makeText(getContext(),errorMessage.toString(),Toast.LENGTH_SHORT).show();
    }
}
