package net.jmreyes.tutela.api;

import net.jmreyes.tutela.model.Medication;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Path;

import java.util.List;

/**
 * Created by juanma on 30/10/14.
 */
public interface MedicationService {

    @Headers({
            "Authorization: Bearer 1e1fafc9-3fd6-4ffe-9566-f9c2e7b57bca"
    })
    @GET("/me/{id}/medication")
    void listMedication(@Path("id") String user, Callback<List<Medication>> callback);
}
