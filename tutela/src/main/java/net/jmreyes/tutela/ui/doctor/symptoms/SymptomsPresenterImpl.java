package net.jmreyes.tutela.ui.doctor.symptoms;

import javax.inject.Inject;

/**
 * Created by juanma on 8/11/14.
 */
public class SymptomsPresenterImpl implements SymptomsPresenter, SymptomsPresenter.OnFinishedListener {
    private SymptomsView view;
    private SymptomsInteractor symptomsInteractor;

    @Inject
    public SymptomsPresenterImpl(SymptomsView view) {
        this.view = view;
        symptomsInteractor = new SymptomsInteractorImpl();
    }
}
