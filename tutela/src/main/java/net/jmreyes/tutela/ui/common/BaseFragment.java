package net.jmreyes.tutela.ui.common;

import android.app.Fragment;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.InjectViews;
import butterknife.Optional;
import fr.castorflex.android.circularprogressbar.CircularProgressBar;
import fr.castorflex.android.circularprogressbar.CircularProgressDrawable;
import net.jmreyes.tutela.R;

/**
 * Created by juanma on 28/10/14.
 */
public class BaseFragment extends Fragment {
    @Optional @InjectView(R.id.loadingLayout) ViewGroup loadingLayout;
    @Optional @InjectView(R.id.progressBar) CircularProgressBar progressBar;
    @Optional @InjectView(R.id.loadingLayoutErrorText) TextView loadingLayoutErrorText;
    @Optional @InjectView(R.id.loadingLayoutRetryButton) TextView loadingLayoutRetryButton;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((BaseActivity) getActivity()).inject(this);
    }

    protected void showLoadingBar() {
        loadingLayout.setVisibility(View.VISIBLE);
    }

    protected void hideLoadingBar() {
        loadingLayout.setVisibility(View.GONE);
    }

    protected void showErrorInLoadingBar(String error) {
        progressBar.setVisibility(View.GONE);

        if (error != null) {
            loadingLayoutErrorText.setText(error);
        }

        loadingLayoutErrorText.setVisibility(View.VISIBLE);
        loadingLayoutRetryButton.setVisibility(View.VISIBLE);
    }

    protected void hideErrorInLoadingBar() {
        progressBar.setVisibility(View.VISIBLE);


        loadingLayoutErrorText.setVisibility(View.GONE);
        loadingLayoutRetryButton.setVisibility(View.GONE);
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}