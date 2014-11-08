package net.jmreyes.tutela.api.services;

import net.jmreyes.tutela.model.Doctor;
import net.jmreyes.tutela.model.Patient;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by juanma on 30/10/14.
 */
public interface PatientService {

    @GET("/patient/{id}")
    void getPatient(@Path("id") String id, Callback<Patient> callback);
}
