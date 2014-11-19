package net.jmreyes.tutela.model.extra;

import java.util.Collection;

/**
 * Created by juanma on 17/11/14.
 */
public class CheckInProposal {
    private String treatmentId;
    private String patientId;

    private Collection<EmbeddedMedicationProposal> medication;
    private Collection<EmbeddedSymptomProposal> symptoms;

    public String getTreatmentId() {
        return treatmentId;
    }

    public String getPatientId() {
        return patientId;
    }

    public static class EmbeddedMedicationProposal {
        private String medicationId;
        private String medicationName;

        public String getMedicationId() {
            return medicationId;
        }
        public String getMedicationName() {
            return medicationName;
        }
    }

    public static class EmbeddedSymptomProposal {
        private String symptomId;
        private String symptomName;
        private String question;
        private Collection<Answer> answers;

        public String getSymptomId() {
            return symptomId;
        }
        public String getSymptomName() {
            return symptomName;
        }
        public String getQuestion() {
            return question;
        }
        public Collection<Answer> getAnswers() {
            return answers;
        }
    }

    public Collection<EmbeddedMedicationProposal> getMedication() {
        return medication;
    }

    public Collection<EmbeddedSymptomProposal> getSymptoms() {
        return symptoms;
    }
}
