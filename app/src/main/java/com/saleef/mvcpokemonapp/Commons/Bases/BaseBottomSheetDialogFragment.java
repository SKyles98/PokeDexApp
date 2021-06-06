package com.saleef.mvcpokemonapp.Commons.Bases;



import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import com.saleef.mvcpokemonapp.Commons.DependencyInjection.ControllerCompositionRoot;
import com.saleef.mvcpokemonapp.MainActivity;

// The purpose of thsese base classes are to get access to our controller compositon Root which is our Homemade dependency injection
public class BaseBottomSheetDialogFragment extends BottomSheetDialogFragment {

    private ControllerCompositionRoot mControllerCompositionRoot;

    protected  ControllerCompositionRoot getControllerCompositionRoot(){
            if (mControllerCompositionRoot==null){
                mControllerCompositionRoot = new ControllerCompositionRoot(((MainActivity) requireActivity()).getActivityCompositionRoot());
            } else{
                return mControllerCompositionRoot;
            }
            return  mControllerCompositionRoot;
    }



}
