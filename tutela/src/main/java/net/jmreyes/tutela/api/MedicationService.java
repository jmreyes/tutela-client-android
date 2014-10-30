package net.jmreyes.tutela.api;

import net.jmreyes.tutela.model.Medication;
import retrofit.http.GET;
import retrofit.http.Path;

import java.util.List;

/**
 * Created by juanma on 30/10/14.
 */
public interface MedicationService {

    @GET("/{user}/medication")
    List<Medication> listMedication(@Path("user") String user);
}
