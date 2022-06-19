package br.ufmg.watchdogs.server.api.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "vaccines")
public class Vaccine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(
            fetch = FetchType.EAGER,
            cascade = CascadeType.PERSIST
    )
    private Animal animal;

    private String name;
    private String infos;
    private LocalDateTime nextDoseDate;
    private LocalDateTime creationDate;
    private LocalDateTime lastUpdateDate;

    public Vaccine() {
    }

    public Vaccine(
            Long id,
            Animal animal,
            String name,
            String infos,
            LocalDateTime nextDoseDate,
            LocalDateTime creationDate,
            LocalDateTime lastUpdateDate
    ) {
        this.id = id;
        this.animal = animal;
        this.name = name;
        this.infos = infos;
        this.nextDoseDate = nextDoseDate;
        this.creationDate = creationDate;
        this.lastUpdateDate = lastUpdateDate;
    }

    public Long getId() {
        return id;
    }

    public Vaccine setId(Long id) {
        this.id = id;
        return this;
    }

    public Animal getAnimal() {
        return animal;
    }

    public Vaccine setAnimal(Animal animal) {
        this.animal = animal;
        return this;
    }

    public String getName() {
        return name;
    }

    public Vaccine setName(String name) {
        this.name = name;
        return this;
    }

    public String getInfos() {
        return infos;
    }

    public Vaccine setInfos(String infos) {
        this.infos = infos;
        return this;
    }

    public LocalDateTime getNextDoseDate() {
        return nextDoseDate;
    }

    public Vaccine setNextDoseDate(LocalDateTime nextDoseDate) {
        this.nextDoseDate = nextDoseDate;
        return this;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public Vaccine setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public LocalDateTime getLastUpdateDate() {
        return lastUpdateDate;
    }

    public Vaccine setLastUpdateDate(LocalDateTime lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
        return this;
    }
}
