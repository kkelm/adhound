package com.adhound.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "location_contact", schema = "adhound", catalog = "")
@IdClass(LocationContactEntityPK.class)
public class LocationContact {
    private int locationId;
    private int contactId;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    @Id
    @Column(name = "contact_id")
    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocationContact that = (LocationContact) o;
        return locationId == that.locationId &&
                contactId == that.contactId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(locationId, contactId);
    }
}
