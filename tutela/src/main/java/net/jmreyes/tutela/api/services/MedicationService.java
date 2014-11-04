package net.jmreyes.tutela.api.services;

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
            "Authorization: Bearer 44d31797-8749-43aa-850b-9feb74459df9"
    })
    @GET("/me/{id}/medication")
    void listMedication(@Path("id") String user, Callback<List<Medication>> callback);

    @Headers({
            "Authorization: Bearer 44d31797-8749-43aa-850b-9feb74459df9"
    })
    @GET("/medication/{id}")
    void findOneById(@Path("id") String user, Callback<Medication> callback);
}
