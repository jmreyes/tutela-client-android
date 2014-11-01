package net.jmreyes.tutela.ui.patient.main.presenter;

import net.jmreyes.tutela.ui.common.BaseFragmentPresenter;
import net.jmreyes.tutela.ui.patient.main.interactor.MyMedicationInteractor;
import net.jmreyes.tutela.ui.patient.main.view.MyMedicationView;

/**
 * Created by juanma on 29/10/14.
 */
public interface MyMedicationPresenter extends BaseFragmentPresenter<MyMedicationView> {

    public void requestMyMedication();

    public static interface OnFinishedListener {
        public void requestMyMedicationFinished();
    }
}
