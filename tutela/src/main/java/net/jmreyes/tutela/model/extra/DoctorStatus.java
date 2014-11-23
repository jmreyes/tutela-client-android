package net.jmreyes.tutela.model.extra;

import net.jmreyes.tutela.model.Alert;

import java.util.Collection;

/**
 * Created by juanma on 9/11/14.
 */
public class DoctorStatus {
    private String username;
    private String name;
    private int unseenAlerts;

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public int getUnseenAlerts() {
        return unseenAlerts;
    }
}
