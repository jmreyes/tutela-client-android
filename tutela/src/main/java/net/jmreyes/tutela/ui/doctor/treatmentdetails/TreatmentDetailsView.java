package net.jmreyes.tutela.ui.doctor.treatmentdetails;

import net.jmreyes.tutela.model.Treatment;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by juanma on 3/11/14.
 */
public interface TreatmentDetailsView {
    public void displayResults(Treatment treatment,
                               HashMap<String, ArrayList<String[]>> medicationHistory,
                               HashMap<String, ArrayList<String[]>> symptomsHistory,
                               ArrayList<ArrayList<String>> medicationFromDoctor,
                               ArrayList<ArrayList<String>> symptomsFromDoctor);

    public void displayResultsError();

    public void saveDetailsSuccess();

    public void saveDetailsError();
}
