package net.jmreyes.tutela.ui.common;

import android.app.Fragment;
import android.os.Bundle;

/**
 * Created by juanma on 28/10/14.
 */
public class BaseFragment extends Fragment {
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((BaseActivity) getActivity()).inject(this);
    }
}