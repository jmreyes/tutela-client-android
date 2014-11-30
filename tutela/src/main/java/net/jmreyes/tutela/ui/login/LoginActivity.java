package net.jmreyes.tutela.ui.login;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import net.jmreyes.tutela.R;
import net.jmreyes.tutela.api.ApiManager;
import net.jmreyes.tutela.ui.common.BaseActivity;
import net.jmreyes.tutela.ui.doctor.main.DoctorMainActivity;
import net.jmreyes.tutela.ui.patient.main.PatientMainActivity;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;

/**
 * A login screen that offers login via email/password.

 */
public class LoginActivity extends BaseActivity implements LoginView {

    public static final String ARG_AUTHTOKEN_TYPE = "paramAuthTokenType";

    @Inject
    LoginPresenter presenter;

    @InjectView(R.id.email) EditText emailText;
    @InjectView(R.id.password) EditText passwordText;
    @InjectView(R.id.login_progress) View progressView;
    @InjectView(R.id.login_form) View loginFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        for (Account account : accountManager.getAccounts()) {
            if (ApiManager.ACCOUNT_TYPE.equals(account.type)) {
                String role = accountManager.getUserData(account, ApiManager.ACCOUNT_ROLE);
                loadActivityFromRole(role);
                overridePendingTransition(0, 0);
                return;
            }
        }
    }

    @Override
    protected List<Object> getModules() {
        return Arrays.<Object>asList(new LoginModule(this));
    }

//    @OnEditorAction(R.id.password)
//    public boolean OnEditorAction(int id, KeyEvent keyEvent) {
//        if (id == R.id.login || id == EditorInfo.IME_NULL) {
//            attemptLogin();
//            return true;
//        }
//        return false;
//    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    @OnClick(R.id.email_sign_in_button)
    public void attemptLogin() {
        // Reset errors.
        emailText.setError(null);
        passwordText.setError(null);

        // Store values at the time of the login attempt.
        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();

        boolean cancel = false;
        View focusView = null;


        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            passwordText.setError(getString(R.string.error_invalid_password));
            focusView = passwordText;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            emailText.setError(getString(R.string.error_field_required));
            focusView = emailText;
            cancel = true;
        } else if (!isEmailValid(email)) {
            emailText.setError(getString(R.string.error_invalid_email));
            focusView = emailText;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            presenter.doLogin(email, password);
        }
    }
    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 3;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            loginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            loginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    loginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            progressView.setVisibility(show ? View.VISIBLE : View.GONE);
            progressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    progressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            progressView.setVisibility(show ? View.VISIBLE : View.GONE);
            loginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    @Override
    public void loginSuccess(String accessToken, String email) {
        AccountManager accountManager = AccountManager.get(this);

        Account account = addOrFindAccount(email, accountManager);
        accountManager.setAuthToken(account, ApiManager.AUTHTOKEN_TYPE, accessToken);

        final Intent intent = new Intent();
        intent.putExtra(AccountManager.KEY_ACCOUNT_NAME, email);
        intent.putExtra(AccountManager.KEY_ACCOUNT_TYPE, ApiManager.ACCOUNT_TYPE);
        if (accessToken != null)
            intent.putExtra(AccountManager.KEY_AUTHTOKEN, accessToken);

        setResult(Activity.RESULT_OK, intent);

        presenter.getRole();
    }

    private Account addOrFindAccount(String email, AccountManager accountManager) {

        Account[] accounts = accountManager.getAccountsByType(ApiManager.ACCOUNT_TYPE);
        Account account = accounts.length != 0 ? accounts[0] :
                new Account(email, ApiManager.ACCOUNT_TYPE);

        if (accounts.length == 0) {
            accountManager.addAccountExplicitly(account, null, null);
        }

        return account;
    }


    @Override
    public void loginError() {
        showProgress(false);

        passwordText.setError(getString(R.string.error_incorrect_password));
        passwordText.requestFocus();
    }

    @Override
    public void getRoleSuccess(String role) {
        showProgress(false);

        Account[] accounts = accountManager.getAccountsByType(ApiManager.ACCOUNT_TYPE);
        Account account = accounts[0];

        accountManager.setUserData(account, ApiManager.ACCOUNT_ROLE, role);

        loadActivityFromRole(role);
    }

    public void loadActivityFromRole(String role) {
        finish();
        overridePendingTransition(0, 0);

        if (role.equals(ApiManager.ROLE_PATIENT)) {
            startActivity(new Intent(this, PatientMainActivity.class));
        } else if (role.equals(ApiManager.ROLE_DOCTOR)) {
            startActivity(new Intent(this, DoctorMainActivity.class));
        }
    }

    protected boolean requireLogin() {
        return false;
    }

}



