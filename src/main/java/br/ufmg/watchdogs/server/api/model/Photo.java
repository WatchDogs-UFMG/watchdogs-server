package br.ufmg.watchdogs.server.api.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "photos")
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(
            fetch = FetchType.EAGER,
            cascade = CascadeType.PERSIST
    )
    private Animal animal;

    @OneToOne(
            fetch = FetchType.EAGER,
            cascade = CascadeType.PERSIST,
            mappedBy = "photo"
    )
    private FoodRelease foodRelease;

    @ManyToOne(
            fetch = FetchType.EAGER,
            cascade = CascadeType.PERSIST
    )
    private Spot spot;

    private String name;
    private LocalDateTime creationDate;
    private LocalDateTime lastUpdateDate;

    public Photo() {
    }

    public Photo(
            Long id,
            Animal animal,
            FoodRelease foodRelease,
            Spot spot,
            String name,
            LocalDateTime creationDate,
            LocalDateTime lastUpdateDate
    ) {
        this.id = id;
        this.animal = animal;
        this.foodRelease = foodRelease;
        this.spot = spot;
        this.name = name;
        this.creationDate = creationDate;
        this.lastUpdateDate = lastUpdateDate;
    }

    public Long getId() {
        return id;
    }

    public Photo setId(Long id) {
        this.id = id;
        return this;
    }

    public Animal getAnimal() {
        return animal;
    }

    public Photo setAnimal(Animal animal) {
        this.animal = animal;
        return this;
    }

    public FoodRelease getFoodRelease() {
        return foodRelease;
    }

    public Photo setFoodRelease(FoodRelease foodRelease) {
        this.foodRelease = foodRelease;
        return this;
    }

    public Spot getSpot() {
        return spot;
    }

    public Photo setSpot(Spot spot) {
        this.spot = spot;
        return this;
    }

    public String getName() {
        return name;
    }

    public Photo setName(String name) {
        this.name = name;
        return this;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public Photo setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public LocalDateTime getLastUpdateDate() {
        return lastUpdateDate;
    }

    public Photo setLastUpdateDate(LocalDateTime lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
        return this;
    }
}
