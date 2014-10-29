package net.jmreyes.tutela.ui.common;

/**
 * Created by juanma on 29/10/14.
 */
public interface BaseFragmentPresenter<T> {
    void init(T view);
}