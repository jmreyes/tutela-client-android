package net.jmreyes.tutela.ui.patient.main.checkin;

import net.jmreyes.tutela.model.extra.Answer;

import java.util.Collection;

/**
 * Created by juanma on 28/10/14.
 */
public interface CheckInView {
    public void displayMedication(String medicationName);
    public void displaySymptom(String question, Collection<Answer> answers);
    public void displayOkay(boolean justCheckedIn);

    public void updateFooter(int questionsLeft, boolean showPreviousButton);

    public void displayError();
    public void displayLoadingBar();
}
