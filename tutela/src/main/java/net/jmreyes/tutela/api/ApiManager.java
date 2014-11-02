package net.jmreyes.tutela.api;

import retrofit.RestAdapter;

/**
 * Created by juanma on 30/10/14.
 */
public class ApiManager {
    private static final String API_URL = "http://192.168.178.59:8443";

    private static final RestAdapter REST_ADAPTER = new RestAdapter.Builder()
            .setEndpoint(API_URL)
            .setLogLevel(RestAdapter.LogLevel.FULL)
            .build();

    private static final MedicationService MEDICATION_SERVICE = REST_ADAPTER.create(MedicationService.class);

    public static MedicationService getMedicationService() {
        return MEDICATION_SERVICE;
    }
}
