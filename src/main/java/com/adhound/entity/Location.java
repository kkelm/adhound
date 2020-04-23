package com.adhound.entity;

import com.adhound.persistence.LocationData;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity(name = "Location")
@Table(name = "locations")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    @ManyToOne
    private User user;

    @NotNull
    @NotEmpty(message = "Enter a Location Name")
    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private String phone;

    @Column(name = "fax")
    private String fax;

    @Column(name = "address")
    private String address;
    @NotNull
    @NotEmpty(message = "Enter a City")
    @Column(name = "city")
    private String city;

    @Column(name = "state_id")
    private int stateId;

    @OneToOne
    @JoinColumn(name = "state_id", insertable = false, updatable = false, nullable = false)
    private State state;

    @NotNull
    @NotEmpty(message = "Enter a Zipcode")
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

    /**
     * Gets locations for a specific user.
     *
     * @param user the User object
     * @return the user locations
     */
    public Set<Location> getLocations(User user) {
        LocationData locationData = new LocationData();
        List<Location> allLocations = locationData.crud.getAll();

        Set<Location> locations = new HashSet<>();

        for (Location location : allLocations) {
            if (location.getUser().equals(user)) {
                Location userLocation = (Location) locationData.crud.getById(location.getId());
                locations.add(userLocation);
            }
        }

        return locations;
    }

    // mappedBy refers to the location_contact table
    //// CascadeType.ALL removes locations associated with the user, orphanRemoval does the same in hibernate
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "location_contact", joinColumns = {
            @JoinColumn(name = "location_id")
    }, inverseJoinColumns = {
            @JoinColumn(name = "contact_id")
    })
    private Set<LocationContact> locationContacts;

    public Set<LocationContact> getLocationContacts() { return locationContacts; }
    public void setLocationContacts(Set<LocationContact> locationContacts) { this.locationContacts = locationContacts; }

    //@JsonIgnore


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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return id == location.id &&
                Objects.equals(name, location.name) &&
                Objects.equals(phone, location.phone) &&
                Objects.equals(fax, location.fax) &&
                Objects.equals(address, location.address) &&
                Objects.equals(city, location.city) &&
                Objects.equals(stateId, location.stateId) &&
                Objects.equals(zipcode, location.zipcode) &&
                Objects.equals(regionId, location.regionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, phone, fax, address, city, stateId, zipcode, regionId);
    }
}
