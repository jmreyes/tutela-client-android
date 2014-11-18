package net.jmreyes.tutela.model;

/**
 * Created by juanma on 4/11/14.
 */
public class Doctor {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

    public String getId() {
        return this.id;
    }
    public String getName() {
        return firstName + " " + lastName;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getEmail() {
        return this.email;
    }
    public String getPhoneNumber() {
        return this.phoneNumber;
    }
}
