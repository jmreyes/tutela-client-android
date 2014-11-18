package net.jmreyes.tutela.model.extra;

import net.jmreyes.tutela.model.PatientDetails;
import net.jmreyes.tutela.model.Treatment;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by juanma on 18/11/14.
 */
public class MyDoctor {
    private String id;
    private String name;

    public MyDoctor(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public static ArrayList<MyDoctor> createListFromPatientDetails(Collection<PatientDetails> patientDetails) {
        ArrayList<MyDoctor> result = new ArrayList<MyDoctor>();

        for (PatientDetails p : patientDetails) {
            result.add(new MyDoctor(p.getDoctorId(),p.getDoctorName()));
        }

        return result;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
