package net.jmreyes.tutela.api.services;

import net.jmreyes.tutela.model.Doctor;
import net.jmreyes.tutela.model.Medication;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Path;

import java.util.List;

/**
 * Created by juanma on 30/10/14.
 */
public interface DoctorService {

    @GET("/me/doctors")
    void getMyDoctors(Callback<List<Doctor>> callback);

    @GET("/doctor/{id}")
    void findOneById(@Path("id") String id, Callback<Doctor> callback);
}
