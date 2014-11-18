package net.jmreyes.tutela.model;

import java.util.Date;

/**
 * Created by juanma on 17/11/14.
 */
public class Alert {
    private String id;
    private String doctorId;
    private String patientId;
    private String patientName;
    private String treatmentId;
    private boolean seen;
    private int hours;
    private String ansText;
    private Date date;

    public String getId() {
        return id;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public String getPatientId() {
        return patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getTreatmentId() {
        return treatmentId;
    }

    public boolean isSeen() {
        return seen;
    }

    public int getHours() {
        return hours;
    }

    public String getAnsText() {
        return ansText;
    }

    public Date getDate() {
        return date;
    }
}
