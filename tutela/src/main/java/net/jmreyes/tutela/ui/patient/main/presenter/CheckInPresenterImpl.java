package net.jmreyes.tutela.ui.patient.main.presenter;

import android.util.Log;
import net.jmreyes.tutela.model.CheckIn;
import net.jmreyes.tutela.model.extra.Answer;
import net.jmreyes.tutela.model.extra.CheckInProposal;
import net.jmreyes.tutela.ui.patient.main.interactor.CheckInInteractor;
import net.jmreyes.tutela.ui.patient.main.interactor.CheckInInteractorImpl;
import net.jmreyes.tutela.ui.patient.main.view.CheckInView;

import javax.inject.Inject;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * Created by juanma on 29/10/14.
 */
public class CheckInPresenterImpl implements CheckInPresenter, CheckInPresenter.OnFinishedListener {
    private CheckInView view;
    private CheckInInteractor checkInInteractor;

    private ArrayList<TreatmentMedicationPair> medications;
    private ArrayList<TreatmentSymptomPair> symptoms;

    private int total;
    private int count;

    private HashMap<String, CheckIn> mapTreatmentCheckIn;

    @Inject
    public CheckInPresenterImpl() {
        checkInInteractor = new CheckInInteractorImpl();
    }

    @Override
    public void init(CheckInView view) {
        this.view = view;
    }

    @Override
    public void makeRequest() {
        checkInInteractor.makeRequest(this);
    }

    @Override
    public void registerMedication(boolean taken) {
        CheckInProposal.EmbeddedMedicationProposal emp = medications.get(count).getMedication();
        String medicationId = emp.getMedicationId();
        String medicationName = emp.getMedicationName();

        CheckIn.EmbeddedMedication em = new CheckIn.EmbeddedMedication(medicationId, medicationName, taken);

        mapTreatmentCheckIn.get(medications.get(count).getTreatmentId()).getMedication().add(em);

        count++;
        nextStep();
    }

    @Override
    public void registerSymptom(int ansIndex, String ansText) {
        CheckInProposal.EmbeddedSymptomProposal esp = symptoms.get(count).getSymptom();
        String symptomId = esp.getSymptomId();
        String symptomName = esp.getSymptomName();

        CheckIn.EmbeddedSymptom es = new CheckIn.EmbeddedSymptom(symptomId, symptomName, ansText, ansIndex);

        mapTreatmentCheckIn.get(symptoms.get(total - count).getTreatmentId()).getSymptoms().add(es);

        count++;
        nextStep();
    }

    /**
     * OnFinishedListener methods, callbacks from the interactor.
     *
     **/

    @Override
    public void onSuccess(ArrayList<CheckInProposal> results) {
        mapTreatmentCheckIn = createMapTreatmentCheckInFromProposal(results);

        medications = extractMedicationsFromProposal(results);
        symptoms = extractSymptomsFromProposal(results);

        total = medications.size() + symptoms.size();
        count = 0;

        Log.d("HOLA", "ITEMS IN CHECKIN" + total);

        if (total > 0)
            nextStep();
    }

    @Override
    public void onError() {
        //view.displayError();
    }

    @Override
    public void onCheckInSuccess() {
        view.displayOkay();
    }

    private void nextStep() {
        if (count < medications.size()) {
            view.displayMedication(medications.get(count).getMedication().getMedicationName());
        } else if (count < total) {
            CheckInProposal.EmbeddedSymptomProposal esp = symptoms.get(total-count).getSymptom();
            view.displaySymptom(esp.getQuestion(), esp.getAnswers());
        } else {
            checkInInteractor.sendCheckIn(new ArrayList<CheckIn>(mapTreatmentCheckIn.values()), this);
        }
    }

    private HashMap<String, CheckIn> createMapTreatmentCheckInFromProposal(ArrayList<CheckInProposal> checkInProposals) {
        HashMap<String, CheckIn> result = new HashMap<String, CheckIn>();

        for (CheckInProposal cp : checkInProposals) {
            CheckIn ci = new CheckIn(cp.getTreatmentId(), null, null,
                    new ArrayList<CheckIn.EmbeddedMedication>(),
                    new ArrayList<CheckIn.EmbeddedSymptom>());
            result.put(cp.getTreatmentId(), ci);
        }

        return result;
    }

    private ArrayList<TreatmentMedicationPair> extractMedicationsFromProposal(ArrayList<CheckInProposal> checkInProposals) {
        ArrayList<TreatmentMedicationPair> result = new ArrayList<TreatmentMedicationPair>();

        for (CheckInProposal cp : checkInProposals) {
            for (CheckInProposal.EmbeddedMedicationProposal emp : cp.getMedication()) {
                result.add(new TreatmentMedicationPair(emp, cp.getTreatmentId()));
            }
        }

        return result;
    }

    private ArrayList<TreatmentSymptomPair> extractSymptomsFromProposal(ArrayList<CheckInProposal> checkInProposals) {
        ArrayList<TreatmentSymptomPair> result = new ArrayList<TreatmentSymptomPair>();

        for (CheckInProposal cp : checkInProposals) {
            for (CheckInProposal.EmbeddedSymptomProposal esp : cp.getSymptoms()) {
                result.add(new TreatmentSymptomPair(esp, cp.getTreatmentId()));
            }
        }

        return result;
    }

    private static class TreatmentMedicationPair {
        private CheckInProposal.EmbeddedMedicationProposal medication;
        private String treatmentId;

        private TreatmentMedicationPair(CheckInProposal.EmbeddedMedicationProposal medication, String treatmentId) {
            this.medication = medication;
            this.treatmentId = treatmentId;
        }

        public CheckInProposal.EmbeddedMedicationProposal getMedication() {
            return medication;
        }

        public String getTreatmentId() {
            return treatmentId;
        }
    }

    private static class TreatmentSymptomPair {
        private CheckInProposal.EmbeddedSymptomProposal symptom;
        private String treatmentId;

        private TreatmentSymptomPair(CheckInProposal.EmbeddedSymptomProposal symptom, String treatmentId) {
            this.symptom = symptom;
            this.treatmentId = treatmentId;
        }

        public CheckInProposal.EmbeddedSymptomProposal getSymptom() {
            return symptom;
        }

        public String getTreatmentId() {
            return treatmentId;
        }
    }
}
