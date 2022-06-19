package br.ufmg.watchdogs.server.api.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "animals")
public class Animal {

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

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Vaccine> vaccines = new ArrayList<>();

    private String animalID;
    private AnimalSize size;
    private String name;
    private Boolean isCastrated;
    private LocalDateTime creationDate;
    private LocalDateTime lastUpdateDate;

    public Animal() {
    }

    public Animal(
            Long id,
            List<FoodRelease> foodReleases,
            List<Photo> photos,
            List<Vaccine> vaccines,
            String animalID,
            AnimalSize size,
            String name,
            Boolean isCastrated,
            LocalDateTime creationDate,
            LocalDateTime lastUpdateDate
    ) {
        this.id = id;
        this.foodReleases = foodReleases;
        this.photos = photos;
        this.vaccines = vaccines;
        this.animalID = animalID;
        this.size = size;
        this.name = name;
        this.isCastrated = isCastrated;
        this.creationDate = creationDate;
        this.lastUpdateDate = lastUpdateDate;
    }

    public Long getId() {
        return id;
    }

    public Animal setId(Long id) {
        this.id = id;
        return this;
    }

    public List<FoodRelease> getFoodReleases() {
        return foodReleases;
    }

    public Animal setFoodReleases(List<FoodRelease> foodReleases) {
        this.foodReleases = foodReleases;
        return this;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public Animal setPhotos(List<Photo> photos) {
        this.photos = photos;
        return this;
    }

    public List<Vaccine> getVaccines() {
        return vaccines;
    }

    public Animal setVaccines(List<Vaccine> vaccines) {
        this.vaccines = vaccines;
        return this;
    }

    public String getAnimalID() {
        return animalID;
    }

    public Animal setAnimalID(String animalID) {
        this.animalID = animalID;
        return this;
    }

    public AnimalSize getSize() {
        return size;
    }

    public Animal setSize(AnimalSize size) {
        this.size = size;
        return this;
    }

    public String getName() {
        return name;
    }

    public Animal setName(String name) {
        this.name = name;
        return this;
    }

    public Boolean getCastrated() {
        return isCastrated;
    }

    public Animal setCastrated(Boolean castrated) {
        isCastrated = castrated;
        return this;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public Animal setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public LocalDateTime getLastUpdateDate() {
        return lastUpdateDate;
    }

    public Animal setLastUpdateDate(LocalDateTime lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
        return this;
    }
}
