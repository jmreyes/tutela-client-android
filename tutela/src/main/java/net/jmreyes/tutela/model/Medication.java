package net.jmreyes.tutela.model;

/**
 * Created by juanma on 29/10/14.
 */
public class Medication {
    private String id;

    private String doctorId;
    private String name;

    public Medication(String id, String doctorId, String name) {
        super();
        this.id = id;
        this.doctorId = doctorId;
        this.name = name;
    }

    public Medication() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
