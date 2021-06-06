package com.saleef.mvcpokemonapp.Commons.Bases;

import androidx.fragment.app.Fragment;

import com.saleef.mvcpokemonapp.Commons.DependencyInjection.ControllerCompositionRoot;
import com.saleef.mvcpokemonapp.MainActivity;

public class BaseFragment extends Fragment {
    private ControllerCompositionRoot mControllerCompositionRoot;


    protected ControllerCompositionRoot getControllerCompositionRoot() {
        if (mControllerCompositionRoot==null){
            mControllerCompositionRoot = new ControllerCompositionRoot(((MainActivity) requireActivity()).getActivityCompositionRoot());
        }
        return mControllerCompositionRoot;
    }
}
