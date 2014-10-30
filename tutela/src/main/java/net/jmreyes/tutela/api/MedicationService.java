package net.jmreyes.tutela.api;

import net.jmreyes.tutela.model.Medication;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

import java.util.List;

/**
 * Created by juanma on 30/10/14.
 */
public interface MedicationService {

    @GET("/{user}/medication")
    void listMedication(@Path("user") String user, Callback<List<Medication>> callback);
}
