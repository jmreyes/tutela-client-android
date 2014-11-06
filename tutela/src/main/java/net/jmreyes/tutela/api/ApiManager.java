package net.jmreyes.tutela.api;

import net.jmreyes.tutela.api.services.AuthService;
import net.jmreyes.tutela.api.services.DoctorService;
import net.jmreyes.tutela.api.services.MedicationService;
import retrofit.RestAdapter;

import javax.inject.Inject;

/**
 * Created by juanma on 30/10/14.
 */
public class ApiManager {
    @Inject
    ApiHeaders apiHeaders;

    private static final String API_URL = "http://192.168.178.59:8443";

    public static final String CLIENT_ID = "mobile"; // Auth token type

    public static final String AUTHTOKEN_TYPE = "net.jmreyes.tutela";
    public static final String ACCOUNT_TYPE = AUTHTOKEN_TYPE;


    private static final RestAdapter REST_ADAPTER = new RestAdapter.Builder()
            .setEndpoint(API_URL)
            .setLogLevel(RestAdapter.LogLevel.FULL)
            .build();

    private static final AuthService AUTH_SERVICE = REST_ADAPTER.create(AuthService.class);
    private static final MedicationService MEDICATION_SERVICE = REST_ADAPTER.create(MedicationService.class);
    private static final DoctorService DOCTOR_SERVICE = REST_ADAPTER.create(DoctorService.class);

    public static AuthService getAuthService() {
        return AUTH_SERVICE;
    }
    public static MedicationService getMedicationService() {
        return MEDICATION_SERVICE;
    }
    public static DoctorService getDoctorService() {
        return DOCTOR_SERVICE;
    }
}
