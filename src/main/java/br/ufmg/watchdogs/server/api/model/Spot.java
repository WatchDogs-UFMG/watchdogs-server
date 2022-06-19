package br.ufmg.watchdogs.server.api.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "alimentation_spots")
public class Spot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<FoodRelease> foodReleases = new ArrayList<>();

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Photo> photos = new ArrayList<>();

    @OneToOne(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Address address;

    private String spotID;
    private String name;
    private Boolean enableSpot;
    private Boolean enableFoodRelease;
    private Boolean enableCam;
    private Boolean enableAnimalIdentification;
    private Boolean enablePresenceSensor;
    private Boolean enableFoodLevelSensor;
    private Boolean enableWaterLevelSensor;
    private Boolean enableWiFiSleep;
    private LocalDateTime creationDate;
    private LocalDateTime lastUpdateDate;

    public Spot() {
    }

    public Spot(
            Long id,
            List<FoodRelease> foodReleases,
            List<Photo> photos,
            Address address,
            String spotID,
            String name,
            Boolean enableSpot,
            Boolean enableFoodRelease,
            Boolean enableCam,
            Boolean enableAnimalIdentification,
            Boolean enablePresenceSensor,
            Boolean enableFoodLevelSensor,
            Boolean enableWaterLevelSensor,
            Boolean enableWiFiSleep,
            LocalDateTime creationDate,
            LocalDateTime lastUpdateDate
    ) {
        this.id = id;
        this.foodReleases = foodReleases;
        this.photos = photos;
        this.address = address;
        this.spotID = spotID;
        this.name = name;
        this.enableSpot = enableSpot;
        this.enableFoodRelease = enableFoodRelease;
        this.enableCam = enableCam;
        this.enableAnimalIdentification = enableAnimalIdentification;
        this.enablePresenceSensor = enablePresenceSensor;
        this.enableFoodLevelSensor = enableFoodLevelSensor;
        this.enableWaterLevelSensor = enableWaterLevelSensor;
        this.enableWiFiSleep = enableWiFiSleep;
        this.creationDate = creationDate;
        this.lastUpdateDate = lastUpdateDate;
    }

    public Long getId() {
        return id;
    }

    public Spot setId(Long id) {
        this.id = id;
        return this;
    }

    public List<FoodRelease> getFoodReleases() {
        return foodReleases;
    }

    public Spot setFoodReleases(List<FoodRelease> foodReleases) {
        this.foodReleases = foodReleases;
        return this;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public Spot setPhotos(List<Photo> photos) {
        this.photos = photos;
        return this;
    }

    public Address getAddress() {
        return address;
    }

    public Spot setAddress(Address address) {
        this.address = address;
        return this;
    }

    public String getSpotID() {
        return spotID;
    }

    public Spot setSpotID(String spotID) {
        this.spotID = spotID;
        return this;
    }

    public String getName() {
        return name;
    }

    public Spot setName(String name) {
        this.name = name;
        return this;
    }

    public Boolean getEnableSpot() {
        return enableSpot;
    }

    public Spot setEnableSpot(Boolean enableSpot) {
        this.enableSpot = enableSpot;
        return this;
    }

    public Boolean getEnableFoodRelease() {
        return enableFoodRelease;
    }

    public Spot setEnableFoodRelease(Boolean enableFoodRelease) {
        this.enableFoodRelease = enableFoodRelease;
        return this;
    }

    public Boolean getEnableCam() {
        return enableCam;
    }

    public Spot setEnableCam(Boolean enableCam) {
        this.enableCam = enableCam;
        return this;
    }

    public Boolean getEnableAnimalIdentification() {
        return enableAnimalIdentification;
    }

    public Spot setEnableAnimalIdentification(Boolean enableAnimalIdentification) {
        this.enableAnimalIdentification = enableAnimalIdentification;
        return this;
    }

    public Boolean getEnablePresenceSensor() {
        return enablePresenceSensor;
    }

    public Spot setEnablePresenceSensor(Boolean enablePresenceSensor) {
        this.enablePresenceSensor = enablePresenceSensor;
        return this;
    }

    public Boolean getEnableFoodLevelSensor() {
        return enableFoodLevelSensor;
    }

    public Spot setEnableFoodLevelSensor(Boolean enableFoodLevelSensor) {
        this.enableFoodLevelSensor = enableFoodLevelSensor;
        return this;
    }

    public Boolean getEnableWaterLevelSensor() {
        return enableWaterLevelSensor;
    }

    public Spot setEnableWaterLevelSensor(Boolean enableWaterLevelSensor) {
        this.enableWaterLevelSensor = enableWaterLevelSensor;
        return this;
    }

    public Boolean getEnableWiFiSleep() {
        return enableWiFiSleep;
    }

    public Spot setEnableWiFiSleep(Boolean enableWiFiSleep) {
        this.enableWiFiSleep = enableWiFiSleep;
        return this;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public Spot setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public LocalDateTime getLastUpdateDate() {
        return lastUpdateDate;
    }

    public Spot setLastUpdateDate(LocalDateTime lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
        return this;
    }
}
