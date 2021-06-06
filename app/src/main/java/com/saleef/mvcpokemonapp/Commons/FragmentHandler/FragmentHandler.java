package com.saleef.mvcpokemonapp.Commons.FragmentHandler;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

// Class to handle our fragment Transactions
public class FragmentHandler {

    private final FragmentManager mFragmentManager;
    private final FragmentFrameWrapper mFragmentFrameWrapper;
    public FragmentHandler(FragmentManager fragmentManager,FragmentFrameWrapper fragmentFrameWrapper) {
        mFragmentManager = fragmentManager;
        mFragmentFrameWrapper = fragmentFrameWrapper;
    }


    private FragmentManager getFragmentManager() {
        return mFragmentManager;
    }


    public void replaceFragment(Fragment fragment) {
        beginTransaction(fragment, true, false);
    }

    private int getFragmentFrameId(){
        return mFragmentFrameWrapper.getFrameLayout().getId();
    }

    public void openSheet(BottomSheetDialogFragment bottomSheetDialogFragment){
        bottomSheetDialogFragment.show(getFragmentManager(),"abilityBottomSheet");
    }


    private void beginTransaction(Fragment newFragment, boolean addtoBackStack, boolean clearBackStack) {

        if (clearBackStack) {
            if (mFragmentManager.isStateSaved()) {
                // If the state is saved we can't clear the back stack. Simply not doing this, but
                // still replacing fragment is a bad idea. Therefore we abort the entire operation.
                return;
            }
            mFragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }

        FragmentTransaction ft = getFragmentManager().beginTransaction();

        if (addtoBackStack) {
            ft.addToBackStack(null);
        }

        ft.replace(getFragmentFrameId(), newFragment, null);
        if (mFragmentManager.isStateSaved()) {
            // We acknowledge the possibility of losing this transaction if the app undergoes
            // save&restore flow after it is committed.
            ft.commitAllowingStateLoss();
        } else {
            ft.commit();
        }

    }
}