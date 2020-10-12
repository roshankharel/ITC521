package main.entities;

import java.math.BigInteger;

/**
 * Staff
 * Staff class, a POJO class, represent a staff record in the database
 *
 * @author Roshan Kharel
 */
public class Staff {
    // staff attributes
    private Integer id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String address;
    private State state;
    private String city;
    private BigInteger telephone;

    /**
     * default constructor
     */
    public Staff() {
    }

    /**
     * overloaded constructor with attributes as parameters
     */
    public Staff(Integer id, String firstName, String middleName, String lastName,
                 String address, State state, String city, BigInteger telephone) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.address = address;
        this.state = state;
        this.city = city;
        this.telephone = telephone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public BigInteger getTelephone() {
        return telephone;
    }

    public void setTelephone(BigInteger telephone) {
        this.telephone = telephone;
    }

    /**
     * String representation of the class (for debugging purpose)
     */
    @Override
    public String toString() {
        return "Staff{" +
                       "id=" + id +
                       ", firstName='" + firstName + '\'' +
                       ", middleName='" + middleName + '\'' +
                       ", lastName='" + lastName + '\'' +
                       ", address='" + address + '\'' +
                       ", state=" + state +
                       ", city='" + city + '\'' +
                       ", telephone=" + telephone +
                       '}';
    }
}
