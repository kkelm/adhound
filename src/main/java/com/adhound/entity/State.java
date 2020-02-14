package com.adhound.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity(name = "State")
@Table(name = "states")

public class State {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    @Column(name = "states_Abbreviation")
    private String abbreviation;

    @Column(name = "states_Name")
    private String name;

    //@OneToOne(mappedBy = "state")
    //private User user;

    @ManyToOne
    @JoinColumn(name = "id", insertable = false, updatable = false, nullable = false)
    private User user;

    State() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
