package net.jmreyes.tutela.model.extra;

import net.jmreyes.tutela.model.Treatment;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by juanma on 18/11/14.
 */
public class MyMedication {
    private String id;
    private String name;
    private String treatmentId;
    private String doctorId;
    private String doctorName;
    private String notes;

    public MyMedication(String id, String name, String treatmentId, String doctorId, String doctorName, String notes) {
        this.id = id;
        this.name = name;
        this.treatmentId = treatmentId;
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.notes = notes;
    }

    public static MyMedication createFromTreatment(Treatment treatment, String medicationId) {
        Collection<Treatment.EmbeddedMedication> embeddedMedication = treatment.getMedication();

        Treatment.EmbeddedMedication specificEm = null;
        for (Treatment.EmbeddedMedication em : embeddedMedication) {
            if (medicationId.equals(em.getMedicationId())) {
                specificEm = em;
                break;
            }
        }

        if (specificEm == null) return null;

        return new MyMedication(specificEm.getMedicationId(), specificEm.getMedicationName(),
                treatment.getId(), treatment.getDoctorId(),
                treatment.getDoctorName(), specificEm.getNotes());
    }

    public static ArrayList<MyMedication> createListFromTreatments(Collection<Treatment> treatments) {
        ArrayList<MyMedication> result = new ArrayList<MyMedication>();

        for (Treatment t : treatments) {
            Collection<Treatment.EmbeddedMedication> embeddedMedication = t.getMedication();

            for (Treatment.EmbeddedMedication em : embeddedMedication) {
                result.add(new MyMedication(em.getMedicationId(),
                        em.getMedicationName(), t.getId(), t.getDoctorId(), t.getDoctorName(), em.getNotes()));
            }
        }

        return result;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTreatmentId() {
        return treatmentId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public String getFullName() {
        return doctorName;
    }

    public String getNotes() {
        return notes;
    }
}
