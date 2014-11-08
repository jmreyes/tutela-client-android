package net.jmreyes.tutela.model;

import java.util.List;

/**
 * Created by juanma on 8/11/14.
 */
public class User {
    private String id;
    private List<String> authorities;

    public String getId() {
        return this.id;
    }
    public List<String> getAuthorities() {
        return this.authorities;
    }
}
