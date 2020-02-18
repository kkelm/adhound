package com.adhound.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity(name = "UserRole")
@Table(name = "user_roles")

public class UserRole {

    @Column(name ="id")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    @Id
    private String username;

    @Column(name = "role_name")
    private String roleName = "Distributor";

    /**
     * Instantiates a new User role.
     */
    public UserRole() {}

    /**
     * Instantiates a new User role.
     *
     * @param username string
     */
    public UserRole(String username) {
        this.username = username;
    }

    /**
     * Gets user role id.
     *
     * @return the user role id integer
     */
    public int getId() {
        return id;
    }

    /**
     * Sets user role id.
     *
     * @param id integer
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the username.
     *
     * @return the username string
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username.
     *
     * @param username string
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets user role name.
     *
     * @return the role name string
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * Sets user role name.
     *
     * @param roleName string
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", roleName='" + roleName + '\'' +
                '}';
    }

}
