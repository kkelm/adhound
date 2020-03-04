package com.adhound.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
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

    @Column(name = "zipcode")
    private String zipcode;

/*
    @OneToOne
    @JoinColumn(name = "username", insertable = false, updatable = false, nullable = false)
    private UserRole userRole;
*/
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<UserRole> userRole = new HashSet<>();


    // mappedBy refers to the instance variable in Location
    // CascadeType.ALL removes locations associated with the user, orphanRemoval does the same in hibernate
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Location> locations = new HashSet<>();

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
     * Gets the user id.
     *
     * @return the id integer
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the user id.
     *
     * @param id integer
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the user's username.
     *
     * @return the username string
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the user's username.
     *
     * @param username string
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the user's password.
     *
     * @return the password string
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the user's password.
     *
     * @param password string
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the user's first name.
     *
     * @return the first name string
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the user's first name.
     *
     * @param firstName string
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the user's last name.
     *
     * @return the last name string
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the user's last name.
     *
     * @param lastName string
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the user's phone.
     *
     * @return the phone string
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the user's phone.
     *
     * @param phone string
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Gets the user's fax.
     *
     * @return the fax string
     */
    public String getFax() {
        return fax;
    }

    /**
     * Sets the user's fax.
     *
     * @param fax string
     */
    public void setFax(String fax) {
        this.fax = fax;
    }

    /**
     * Gets the user's email.
     *
     * @return the email string
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the user's email.
     *
     * @param email string
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the user's address.
     *
     * @return the address string
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the user's address.
     *
     * @param address string
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets the user's city.
     *
     * @return the city string
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the user's city.
     *
     * @param city string
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Gets the user's state id.
     *
     * @return the state id integer
     */
    public int getStateId() {
        return stateId;
    }

    /**
     * Sets the user's state id.
     *
     * @param stateId integer
     */
    public void setStateId(int stateId) {
        this.stateId = stateId;
    }

    /**
     * Gets the state object for the user's state.
     *
     * @return state the State object
     */
    public State getState() {
        return state;
    }

    /**
     * Sets the state object for the user's state.
     *
     * @param state the State object
     */
    public void setState(State state) {
        this.state = state;
    }

    /**
     * Gets the user's zipcode.
     *
     * @return the zipcode string
     */
    public String getZipcode() {
        return zipcode;
    }

    /**
     * Sets the user's zipcode.
     *
     * @param zipcode string
     */
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    /**
     * Gets the user role object for the user's role.
     *
     * @return the UserRole object
     */
    public Set<UserRole> getUserRole() { return userRole; }

    /**
     * Sets the user role object for the user's role.
     *
     * @param userRole the UserRole object
     */
    public void setUserRole(Set<UserRole> userRole) {
        this.userRole = userRole;
    }

    public Set<Location> getLocations() {
        return locations;
    }

    public void setLocations(Set<Location> locations) {
        this.locations = locations;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", fax='" + fax + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", stateId=" + stateId +
                ", state=" + state +
                ", zipcode='" + zipcode + '\'' +
                ", locations=" + locations +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(stateId, user.stateId) &&
                Objects.equals(username, user.username) &&
                Objects.equals(password, user.password) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(phone, user.phone) &&
                Objects.equals(fax, user.fax) &&
                Objects.equals(email, user.email) &&
                Objects.equals(address, user.address) &&
                Objects.equals(city, user.city) &&
                Objects.equals(zipcode, user.zipcode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, firstName, lastName, phone, fax, email, address, city, stateId, zipcode);
    }

}
