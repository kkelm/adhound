package com.adhound.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity(name = "Location")
@Table(name = "locations")

public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    @ManyToOne
    private User user;

    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private String phone;

    @Column(name = "fax")
    private String fax;

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

    @Column(name = "region_id")
    private int regionId;

    @OneToOne
    @JoinColumn(name = "region_id", insertable = false, updatable = false, nullable = false)
    private Region region;

    public Location() {}

    public Location(User user, String name, String phone, String fax, String address, String city, int stateId, String zipcode, int regionId) {
        this.user = user;
        this.name = name;
        this.phone = phone;
        this.fax = fax;
        this.address = address;
        this.city = city;
        this.stateId = stateId;
        this.zipcode = zipcode;
        this.regionId = regionId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getStateId() {
        return stateId;
    }

    public void setStateId(int stateId) {
        this.stateId = stateId;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", fax='" + fax + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", stateId=" + stateId +
                ", state=" + state +
                ", zipcode='" + zipcode + '\'' +
                ", regionId=" + regionId +
                ", region=" + region +
                '}';
    }

}
