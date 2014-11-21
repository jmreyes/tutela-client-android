package net.jmreyes.tutela.ui.patient.main.presenter;

import net.jmreyes.tutela.ui.common.BaseFragmentPresenter;
import net.jmreyes.tutela.ui.patient.main.view.MyRemindersView;

/**
 * Created by juanma on 29/10/14.
 */
public interface MyRemindersPresenter extends BaseFragmentPresenter<MyRemindersView> {
    public void getAlarms();
}
