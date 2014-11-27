package net.jmreyes.tutela.ui.doctor.treatmentdetails;

import net.jmreyes.tutela.model.Treatment;

/**
 * Created by juanma on 4/11/14.
 */
public interface TreatmentDetailsInteractor {
    public void makeRequest(String treatmentId, final TreatmentDetailsPresenter.OnFinishedListener listener);

    public void postDetails(Treatment treatment, final TreatmentDetailsPresenter.OnFinishedListener listener);
}
