package net.jmreyes.tutela.ui.doctor.treatmentdetails;

import net.jmreyes.tutela.model.Treatment;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by juanma on 3/11/14.
 */
public interface TreatmentDetailsView {
    public void displayResults(Treatment treatment,
                               HashMap<String, ArrayList<HistoryItem>> medicationHistory,
                               HashMap<String, ArrayList<HistoryItem>> symptomsHistory,
                               ArrayList<ArrayList<String>> medicationFromDoctor,
                               ArrayList<ArrayList<String>> symptomsFromDoctor);

    public void displayResultsError();

    public void saveDetailsSuccess();

    public void saveDetailsError();

    public static class HistoryItem {
        private String date;
        private String answer;
        private long dateLong;
        private int index;

        public HistoryItem(String date, String answer, long dateLong, int index) {
            this.date = date;
            this.answer = answer;
            this.dateLong = dateLong;
            this.index = index;
        }

        public String getDate() {
            return date;
        }

        public String getAnswer() {
            return answer;
        }

        public int getIndex() {
            return index;
        }

        public long getDateLong() {
            return dateLong;
        }
    }
}
