package net.jmreyes.tutela.ui.doctor.symptoms;

import dagger.Module;
import dagger.Provides;
import net.jmreyes.tutela.AppModule;

import javax.inject.Singleton;

/**
 * Created by juanma on 8/11/14.
 */
@Module(
        addsTo = AppModule.class,
        injects = {
                SymptomsActivity.class
        },
        complete = false,
        library = true
)
public class SymptomsModule {

    private SymptomsView view;

    public SymptomsModule(SymptomsView view) {
        this.view = view;
    }

    /**
     * Provide SymptomsView
     */
    @Provides
    @Singleton
    public SymptomsView provideSymptomsView() {
        return view;
    }

    /**
     * Provide SymptomsPresenter
     */
    @Provides
    @Singleton
    public SymptomsPresenter provideSymptomsPresenter(SymptomsView view) {
        return new SymptomsPresenterImpl(view);
    }
}
