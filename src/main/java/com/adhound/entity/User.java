package com.adhound.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "User")
@Table(name = "users")

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone")
    private String phone;

    @Column(name = "fax")
    private String fax;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "state_id")
    private int stateId;

    @OneToOne
    @JoinColumn(name = "state_id", insertable = false, updatable = false, nullable = false)
    private State state;

    //@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    //private Set<State> state;

    @Column(name = "zipcode")
    private String zipcode;

    /**
     * Instantiates a new User.
     */
    public User() {}

    /**
     * Instantiates a new User.
     *
     * @param username  the username
     * @param password  the password
     * @param firstName the first name
     * @param lastName  the last name
     * @param phone     the phone
     * @param fax       the fax
     * @param email     the email
     * @param address   the address
     * @param city      the city
     * @param stateId   the state id
     * @param zipcode   the zipcode
     */
    public User(String username, String password, String firstName, String lastName, String phone, String fax, String email, String address, String city, int stateId, String zipcode) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.fax = fax;
        this.email = email;
        this.address = address;
        this.city = city;
        this.stateId = stateId;
        this.zipcode = zipcode;
    }

    /**
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return
     */
    public String getFax() {
        return fax;
    }

    /**
     * @param fax
     */
    public void setFax(String fax) {
        this.fax = fax;
    }

    /**
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return
     */
    public int getStateId() {
        return stateId;
    }

    /**
     * @param stateId
     */
    public void setStateId(int stateId) {
        this.stateId = stateId;
    }

    /**
     * @return
     */
    public State getState() {
        return state;
    }

    /**
     * @param state
     */
    public void setState(State state) {
        this.state = state;
    }

    /**
     * @return
     */
    public String getZipcode() {
        return zipcode;
    }

    /**
     * @param zipcode
     */
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }



}
