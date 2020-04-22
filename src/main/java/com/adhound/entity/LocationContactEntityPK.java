package com.adhound.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class LocationContactEntityPK implements Serializable {
    private int locationId;
    private int contactId;

    @Column(name = "location_id")
    @Id
    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    @Column(name = "contact_id")
    @Id
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
        LocationContactEntityPK that = (LocationContactEntityPK) o;
        return locationId == that.locationId &&
                contactId == that.contactId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(locationId, contactId);
    }
}
