package net.jmreyes.tutela.api.services;

import net.jmreyes.tutela.model.*;
import net.jmreyes.tutela.model.extra.CheckInProposal;
import net.jmreyes.tutela.model.extra.MyDoctor;
import net.jmreyes.tutela.model.extra.MyMedication;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

import java.util.Collection;

/**
 * Created by juanma on 17/11/14.
 */
public interface PatientSvcApi {
    public static final String PATIENT_SVC_PATH = "/patient";

    public static final String PATIENT_MEDICATION = PATIENT_SVC_PATH + "/me/medication";
    public static final String PATIENT_PATIENTDETAILS = PATIENT_SVC_PATH + "/me/details";
    public static final String PATIENT_DOCTORS = PATIENT_SVC_PATH + "/me/doctors";
    public static final String PATIENT_CHECKIN = PATIENT_SVC_PATH + "/me/checkin";

    @GET(PATIENT_MEDICATION)
    public void getMyMedication(Callback<Collection<MyMedication>> callback);

    @GET(PATIENT_MEDICATION + "/{treatmentId}/{medicationId}")
    public void getOneMedication(@Path("treatmentId") String treatmentId,
                                 @Path("medicationId") String medicationId, Callback<MyMedication> callback);

    @GET(PATIENT_DOCTORS)
    public void getDoctors(Callback<Collection<MyDoctor>> callback);

    @GET(PATIENT_DOCTORS + "/{id}")
    public void getDoctor(@Path("id") String id, Callback<Doctor> callback);

    @GET(PATIENT_CHECKIN)
    public void getCheckInProposal(Callback<Collection<CheckInProposal>> callback);

    @POST(PATIENT_CHECKIN)
    public void postCheckIn(@Body Collection<CheckIn> checkIns, Callback<Void> callback);
}
