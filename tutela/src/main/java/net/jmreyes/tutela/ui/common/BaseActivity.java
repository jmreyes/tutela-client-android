package net.jmreyes.tutela.ui.common;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.OnAccountsUpdateListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.InjectView;
import butterknife.Optional;
import dagger.ObjectGraph;
import fr.castorflex.android.circularprogressbar.CircularProgressBar;
import net.jmreyes.tutela.App;
import net.jmreyes.tutela.R;
import net.jmreyes.tutela.api.ApiManager;
import net.jmreyes.tutela.ui.login.LoginActivity;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by juanma on 28/10/14.
 */
public abstract class BaseActivity extends ActionBarActivity implements OnAccountsUpdateListener {
    @Optional @InjectView(R.id.loadingLayout) ViewGroup loadingLayout;
    @Optional @InjectView(R.id.progressBar) CircularProgressBar progressBar;
    @Optional @InjectView(R.id.loadingLayoutErrorText) TextView loadingLayoutErrorText;
    @Optional @InjectView(R.id.loadingLayoutRetryButton) TextView loadingLayoutRetryButton;

    @Inject
    protected AccountManager accountManager;

    private ObjectGraph activityGraph;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityGraph = ((App) getApplication()).createScopedGraph(getModules().toArray());
        activityGraph.inject(this);
    }

    @Override protected void onResume() {
        super.onResume();

        // Watch to make sure the account still exists.
        if(requireLogin()) accountManager.addOnAccountsUpdatedListener(this, null, true);
    }

    @Override protected void onPause() {
        if(requireLogin()) accountManager.removeOnAccountsUpdatedListener(this);
        super.onPause();
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        activityGraph = null;
    }

    public void inject(Object object) {
        activityGraph.inject(object);
    }

    protected abstract List<Object> getModules();

    protected boolean requireLogin() {
        return true;
    }

    @Override public void onAccountsUpdated(Account[] accounts) {
        for (Account account : accounts) {
            if (ApiManager.ACCOUNT_TYPE.equals(account.type)) {
                return;
            }
        }

        // No accounts so start the authenticator activity
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
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
}