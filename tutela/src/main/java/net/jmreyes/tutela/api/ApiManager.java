package net.jmreyes.tutela.api;

import android.accounts.AccountManager;
import android.app.Application;
import com.squareup.okhttp.OkHttpClient;
import net.jmreyes.tutela.api.services.AuthService;
import net.jmreyes.tutela.api.services.DoctorService;
import net.jmreyes.tutela.api.services.MedicationService;
import retrofit.RestAdapter;
import retrofit.client.OkClient;

import javax.inject.Inject;

/**
 * Created by juanma on 30/10/14.
 */
public class ApiManager {

    private static final String API_URL = "http://192.168.178.59:8443";

    public static final String CLIENT_ID = "mobile"; // Auth token type

    public static final String AUTHTOKEN_TYPE = "net.jmreyes.tutela";
    public static final String ACCOUNT_TYPE = AUTHTOKEN_TYPE;

    private static AuthService authService;
    private static MedicationService medicationService;
    private static DoctorService doctorService;

    public static void initRestAdapter(Application app) {
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setAuthenticator(new ApiAuthenticator(app, AccountManager.get(app)));

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(API_URL)
                .setClient(new OkClient(okHttpClient))
                .setRequestInterceptor(new ApiHeaders(app))
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        authService = restAdapter.create(AuthService.class);
        medicationService = restAdapter.create(MedicationService.class);
        doctorService = restAdapter.create(DoctorService.class);
    }


    public static AuthService getAuthService() {
        return authService;
    }
    public static MedicationService getMedicationService() {
        return medicationService;
    }
    public static DoctorService getDoctorService() {
        return doctorService;
    }
}
