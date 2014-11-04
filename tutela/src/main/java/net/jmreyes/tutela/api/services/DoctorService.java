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

    @Headers({
            "Authorization: Bearer 77ccd3d6-f5dc-4c4c-bf56-67b663ced297"
    })
    @GET("/doctors/{id}")
    void getDoctor(@Path("id") String id, Callback<Doctor> callback);
}
