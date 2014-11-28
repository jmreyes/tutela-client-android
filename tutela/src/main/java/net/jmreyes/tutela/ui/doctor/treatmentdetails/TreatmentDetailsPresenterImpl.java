package net.jmreyes.tutela.ui.doctor.treatmentdetails;

import net.jmreyes.tutela.R;
import net.jmreyes.tutela.model.CheckIn;
import net.jmreyes.tutela.model.Medication;
import net.jmreyes.tutela.model.Symptom;
import net.jmreyes.tutela.model.Treatment;

import javax.inject.Inject;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by juanma on 4/11/14.
 */
public class TreatmentDetailsPresenterImpl implements TreatmentDetailsPresenter, TreatmentDetailsPresenter.OnFinishedListener {
    private TreatmentDetailsView view;
    private TreatmentDetailsInteractor treatmentDetailsInteractor;

    @Inject
    public TreatmentDetailsPresenterImpl(TreatmentDetailsView view) {
        this.view = view;
        treatmentDetailsInteractor = new TreatmentDetailsInteractorImpl();
    }

    @Override
    public void makeRequest(String treatmentId) {
        treatmentDetailsInteractor.makeRequest(treatmentId, this);
    }

    @Override
    public void postDetails(Treatment treatment) {
        treatmentDetailsInteractor.postDetails(treatment, this);
    }

    /**
     * OnFinishedListener methods, callbacks from the interactor.
     *
     **/

    @Override
    public void onSuccess(Treatment treatment, ArrayList<CheckIn> checkIns, ArrayList<Medication> medication, ArrayList<Symptom> symptoms) {
        HashMap<String, ArrayList<TreatmentDetailsView.HistoryItem>> medicationHistory = new HashMap<String, ArrayList<TreatmentDetailsView.HistoryItem>>();
        HashMap<String, ArrayList<TreatmentDetailsView.HistoryItem>> symptomsHistory = new HashMap<String, ArrayList<TreatmentDetailsView.HistoryItem>>();

        ArrayList<ArrayList<String>> medicationFromDoctor = new ArrayList<ArrayList<String>>(2);
        ArrayList<ArrayList<String>> symptomsFromDoctor = new ArrayList<ArrayList<String>>(2);

        for (int i = checkIns.size() - 1; i >= 0; i--) {
            CheckIn ci = checkIns.get(i);
            Collection<CheckIn.EmbeddedMedication> ems = ci.getMedication();

            for (CheckIn.EmbeddedMedication em : ems) {
                String medicationName = em.getMedicationName();
                String date = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(em.getDate());
                String answer = em.getTaken() ? "YES" : "NO";
                int ansIndex = em.getTaken() ? 1 : 0;

                ArrayList<TreatmentDetailsView.HistoryItem> dateAndAnswerArrayList = medicationHistory.get(medicationName);
                if (dateAndAnswerArrayList == null) {
                    dateAndAnswerArrayList = new ArrayList<TreatmentDetailsView.HistoryItem>();
                }
                dateAndAnswerArrayList.add(new TreatmentDetailsView.HistoryItem(date, answer, em.getDate().getTime(), ansIndex));

                medicationHistory.put(medicationName, dateAndAnswerArrayList);
            }

            Collection<CheckIn.EmbeddedSymptom> ess = ci.getSymptoms();

            for (CheckIn.EmbeddedSymptom es : ess) {
                String symptomName = es.getSymptomName();
                String date = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(ci.getDate());
                String answer = es.getAnsText();
                int ansIndex = es.getAnsIndex();

                ArrayList<TreatmentDetailsView.HistoryItem> dateAndAnswerArrayList = symptomsHistory.get(symptomName);
                if (dateAndAnswerArrayList == null) {
                    dateAndAnswerArrayList = new ArrayList<TreatmentDetailsView.HistoryItem>();
                }
                dateAndAnswerArrayList.add(new TreatmentDetailsView.HistoryItem(date, answer, ci.getDate().getTime(), ansIndex));

                symptomsHistory.put(symptomName, dateAndAnswerArrayList);
            }
        }

        ArrayList<String> medicationIds = new ArrayList<String>();
        ArrayList<String> medicationNames = new ArrayList<String>();
        for (Medication m : medication) {
            medicationIds.add(m.getId());
            medicationNames.add(m.getName());
        }
        medicationFromDoctor.add(0, medicationIds);
        medicationFromDoctor.add(1, medicationNames);

        ArrayList<String> symptomIds = new ArrayList<String>();
        ArrayList<String> symptomNames = new ArrayList<String>();
        for (Symptom s : symptoms) {
            symptomIds.add(s.getId());
            symptomNames.add(s.getName());
        }
        symptomsFromDoctor.add(0, symptomIds);
        symptomsFromDoctor.add(1, symptomNames);

        view.displayResults(treatment, medicationHistory, symptomsHistory, medicationFromDoctor, symptomsFromDoctor);
    }

    @Override
    public void onError() {
        view.displayResultsError();
    }

    @Override
    public void onPostDetailsSuccess() {
        view.saveDetailsSuccess();
    }

    @Override
    public void onPostDetailsError() {
        view.saveDetailsError();
    }
}
