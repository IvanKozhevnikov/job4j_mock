package ru.checkdev.auth.domain;

import javax.persistence.*;

/**
 * @author parsentev
 * @since 25.09.2016
 */
@Entity(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "value_role")
    private String value;


    public Role() {
    }

    public Role(int id) {
        this.id = id;
    }

    public Role(String valueRole) {
        this();
        this.value = valueRole;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String valueRole) {
        this.value = valueRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Role role = (Role) o;

        return id == role.id;

    }

    @Override
    public int hashCode() {
        return id;
    }
}
