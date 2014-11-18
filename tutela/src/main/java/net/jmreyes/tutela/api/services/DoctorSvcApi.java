package net.jmreyes.tutela.api.services;

import net.jmreyes.tutela.model.*;
import net.jmreyes.tutela.model.extra.DoctorStatus;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;

import java.util.Collection;

/**
 *
 * @author jmreyes
 *
 */
public interface DoctorSvcApi {
    public static final String DOCTOR_SVC_PATH = "/doctor";

    public static final String DOCTOR_STATUS = DOCTOR_SVC_PATH + "/me/status";
    public static final String DOCTOR_ALERTS = DOCTOR_SVC_PATH + "/me/alerts";
    public static final String DOCTOR_ALERTS_MARK_SEEN = DOCTOR_SVC_PATH + "/me/alerts/seen";
    public static final String DOCTOR_PATIENTS = DOCTOR_SVC_PATH + "/me/patients";
    public static final String DOCTOR_SYMPTOMS = DOCTOR_SVC_PATH + "/me/symptoms";
    public static final String DOCTOR_MEDICATION = DOCTOR_SVC_PATH + "/me/medication";
    public static final String DOCTOR_TREATMENT = DOCTOR_SVC_PATH + "/me/treatment";

    @GET(DOCTOR_STATUS)
    public void getStatus(Callback<DoctorStatus> callback);

    @GET(DOCTOR_ALERTS)
    public void getAlerts(Callback<Collection<Alert>> callback);

    @POST(DOCTOR_ALERTS_MARK_SEEN)
    public void postAlertsMarkAsSeen(Callback<Void> callback);

    @GET(DOCTOR_PATIENTS)
    public void  getPatients(Callback<Collection<PatientDetails>> callback);

    @GET(DOCTOR_PATIENTS + "/{id}")
    public void getPatient(@Path("id") String id, Callback<Collection<Patient>> callback);

    @PUT(DOCTOR_PATIENTS + "/{id}")
    public void updatePatient(@Path("id") String id, @Body PatientDetails patientDetails, Callback<Void> callback);

    @GET(DOCTOR_SYMPTOMS)
    public void getSymptoms(Callback<Collection<Symptom>> callback);

    @POST(DOCTOR_SYMPTOMS)
    public void postSymptom(@Body Symptom symptom, Callback<Void> callback);

    @GET(DOCTOR_SYMPTOMS + "/{id}")
    public void getSymptom(@Path("id") String id, Callback<Symptom> callback);

    @GET(DOCTOR_MEDICATION)
    public void getMedication(Callback<Collection<Medication>> callback);

    @POST(DOCTOR_MEDICATION)
    public void postMedication(@Body Medication medication, Callback<Void> callback);

    @GET(DOCTOR_MEDICATION + "/{id}")
    public void getOneMedication(@Path("id") String id, Callback<Medication> callback);

    @GET(DOCTOR_TREATMENT + "/{id}")
    public void getTreatment(@Path("id") String id, Callback<Treatment> callback);

    @POST(DOCTOR_TREATMENT)
    public void postTreatment(@Body Treatment medication, Callback<Void> callback);
}
