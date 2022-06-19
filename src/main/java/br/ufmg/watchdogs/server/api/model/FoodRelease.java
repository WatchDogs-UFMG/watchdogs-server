package br.ufmg.watchdogs.server.api.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "food_releases")
public class FoodRelease {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(
            fetch = FetchType.EAGER,
            cascade = CascadeType.PERSIST
    )
    private Animal animal;

    @ManyToOne(
            fetch = FetchType.EAGER,
            cascade = CascadeType.PERSIST
    )
    private Spot spot;

    @OneToOne(
            fetch = FetchType.EAGER,
            cascade = CascadeType.PERSIST,
            orphanRemoval = true
    )
    private Photo photo;

    private LocalDateTime creationDate;
    private LocalDateTime lastUpdateDate;

    public FoodRelease() {
    }

    public FoodRelease(
            Long id,
            Animal animal,
            Spot spot,
            Photo photo,
            LocalDateTime creationDate,
            LocalDateTime lastUpdateDate
    ) {
        this.id = id;
        this.animal = animal;
        this.spot = spot;
        this.photo = photo;
        this.creationDate = creationDate;
        this.lastUpdateDate = lastUpdateDate;
    }

    public Long getId() {
        return id;
    }

    public FoodRelease setId(Long id) {
        this.id = id;
        return this;
    }

    public Animal getAnimal() {
        return animal;
    }

    public FoodRelease setAnimal(Animal animal) {
        this.animal = animal;
        return this;
    }

    public Spot getSpot() {
        return spot;
    }

    public FoodRelease setSpot(Spot spot) {
        this.spot = spot;
        return this;
    }

    public Photo getPhoto() {
        return photo;
    }

    public FoodRelease setPhoto(Photo photo) {
        this.photo = photo;
        return this;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public FoodRelease setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public LocalDateTime getLastUpdateDate() {
        return lastUpdateDate;
    }

    public FoodRelease setLastUpdateDate(LocalDateTime lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
        return this;
    }
}
