package net.jmreyes.tutela.model;

/**
 * Created by juanma on 29/10/14.
 */
public class Medication {
    private String id;
    private String name;
    private String doctorName;
    private String frequency;
    private String notes;

    public String getId() {
        return this.id;
    }
    public String getName() {
        return this.name;
    }
    public String getDoctorName() {
        return this.doctorName;
    }
    public String getFrequency() {
        return this.frequency;
    }
    public String getNotes() {
        return this.notes;
    }
}
