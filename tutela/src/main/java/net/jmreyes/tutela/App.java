package net.jmreyes.tutela;

import android.app.Application;
import dagger.ObjectGraph;

import java.util.Arrays;
import java.util.List;

/**
 * Created by juanma on 28/10/14.
 */
public class App extends Application {
    private ObjectGraph objectGraph;

    /**
     * Build object graph on creation so that objects are available
     */
    @Override
    public void onCreate() {
        super.onCreate();
        objectGraph = ObjectGraph.create(getModules().toArray());
        objectGraph.inject(this);
    }

    private List<Object> getModules() {
        return Arrays.<Object>asList(new AppModule(this));
    }

    /**
     * Used by Activities to create a scoped graph
     */
    public ObjectGraph createScopedGraph(Object... modules) {
        return objectGraph.plus(modules);
    }

    public ObjectGraph getMainGraph() {
        return objectGraph;
    }
}
