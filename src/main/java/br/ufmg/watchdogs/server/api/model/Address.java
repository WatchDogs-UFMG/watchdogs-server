package br.ufmg.watchdogs.server.api.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(
            fetch = FetchType.EAGER,
            cascade = CascadeType.PERSIST,
            mappedBy = "address"
    )
    private Spot spot;

    private String number;
    private String street;
    private String neighbourhood;
    private String city;
    private String state;
    private String country;
    private String zipCode;
    private LocalDateTime creationDate;
    private LocalDateTime lastUpdateDate;

    public Address() {
    }

    public Address(
            Long id,
            Spot spot,
            String number,
            String street,
            String neighbourhood,
            String city,
            String state,
            String country,
            String zipCode,
            LocalDateTime creationDate,
            LocalDateTime lastUpdateDate
    ) {
        this.id = id;
        this.spot = spot;
        this.number = number;
        this.street = street;
        this.neighbourhood = neighbourhood;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zipCode = zipCode;
        this.creationDate = creationDate;
        this.lastUpdateDate = lastUpdateDate;
    }

    public Long getId() {
        return id;
    }

    public Address setId(Long id) {
        this.id = id;
        return this;
    }

    public Spot getSpot() {
        return spot;
    }

    public Address setSpot(Spot spot) {
        this.spot = spot;
        return this;
    }

    public String getNumber() {
        return number;
    }

    public Address setNumber(String number) {
        this.number = number;
        return this;
    }

    public String getStreet() {
        return street;
    }

    public Address setStreet(String street) {
        this.street = street;
        return this;
    }

    public String getNeighbourhood() {
        return neighbourhood;
    }

    public Address setNeighbourhood(String neighbourhood) {
        this.neighbourhood = neighbourhood;
        return this;
    }

    public String getCity() {
        return city;
    }

    public Address setCity(String city) {
        this.city = city;
        return this;
    }

    public String getState() {
        return state;
    }

    public Address setState(String state) {
        this.state = state;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public Address setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getZipCode() {
        return zipCode;
    }

    public Address setZipCode(String zipCode) {
        this.zipCode = zipCode;
        return this;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public Address setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public LocalDateTime getLastUpdateDate() {
        return lastUpdateDate;
    }

    public Address setLastUpdateDate(LocalDateTime lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
        return this;
    }
}
