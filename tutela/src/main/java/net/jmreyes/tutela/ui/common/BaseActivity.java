package net.jmreyes.tutela.ui.common;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import dagger.ObjectGraph;
import net.jmreyes.tutela.App;

import java.util.List;

/**
 * Created by juanma on 28/10/14.
 */
public abstract class BaseActivity extends ActionBarActivity {
    private ObjectGraph activityGraph;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityGraph = ((App) getApplication()).createScopedGraph(getModules().toArray());
        activityGraph.inject(this);
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        activityGraph = null;
    }

    public void inject(Object object) {
        activityGraph.inject(object);
    }

    protected abstract List<Object> getModules();
}