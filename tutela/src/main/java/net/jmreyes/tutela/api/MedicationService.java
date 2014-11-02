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
            "Authorization: Bearer 8c5efd95-624f-4601-85c9-77fb324f6b8a"
    })
    @GET("/me/{id}/medication")
    void listMedication(@Path("id") String user, Callback<List<Medication>> callback);
}
