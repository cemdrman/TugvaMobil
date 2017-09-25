package org.tugva.basaksehir.tugvabasaksehir.Others;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

import org.tugva.basaksehir.tugvabasaksehir.R;

/**
 * Created by Emine BABACAN on 29.6.2016.
 */

    public class LoadFragment {
        FragmentManager fragmentManager;
        public LoadFragment(FragmentManager fragmentManager)
        {
            this.fragmentManager = fragmentManager;
        }

        public void initializeFragment(Fragment fragment) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frame_container, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }

