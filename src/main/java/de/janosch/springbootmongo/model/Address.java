package de.janosch.springbootmongo.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

/**
 * Address
 */
public class Address {

    @Id
    private ObjectId id;

    private String street;
    private String postcode;
    private String city;

    private String firstname;
    private String lastname;

    public ObjectId getId() {
        return id;
    }

    public Address id(ObjectId id) {
        this.id = id;
        return this;
    }

    public String getStreet() {
        return street;
    }

    public Address street(String street) {
        this.street = street;
        return this;
    }

    public String getPostcode() {
        return postcode;
    }

    public Address postcode(String postcode) {
        this.postcode = postcode;
        return this;
    }

    public String getCity() {
        return city;
    }

    public Address city(String city) {
        this.city = city;
        return this;
    }

    public String getFirstname() {
        return firstname;
    }

    public Address firstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public String getLastname() {
        return lastname;
    }

    public Address lastname(String lastname) {
        this.lastname = lastname;
        return this;
    }
}