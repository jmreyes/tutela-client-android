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
            "Authorization: Bearer 0fb2d623-9697-4998-8f7b-a973b2cac8c2"
    })
    @GET("/me/{id}/medication")
    void listMedication(@Path("id") String user, Callback<List<Medication>> callback);
}
