package br.ufmg.watchdogs.server.api.model;

import br.ufmg.watchdogs.server.mqtt.protocol.uplink.payload.parser.impl.LogTriggerEventImpl;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "log")
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(
            fetch = FetchType.EAGER,
            cascade = CascadeType.PERSIST
    )
    private Spot spot;

    @Enumerated(EnumType.STRING)
    private LogTriggerEventImpl triggerEvent;

    private LocalDateTime creationDate;

    public Log() {
    }

    public Log(
            Long id,
            Spot spot,
            LogTriggerEventImpl triggerEvent,
            LocalDateTime creationDate
    ) {
        this.id = id;
        this.spot = spot;
        this.triggerEvent = triggerEvent;
        this.creationDate = creationDate;
    }

    public Long getId() {
        return id;
    }

    public Log setId(Long id) {
        this.id = id;
        return this;
    }

    public Spot getSpot() {
        return spot;
    }

    public Log setSpot(Spot spot) {
        this.spot = spot;
        return this;
    }

    public LogTriggerEventImpl getTriggerEvent() {
        return triggerEvent;
    }

    public Log setTriggerEvent(LogTriggerEventImpl triggerEvent) {
        this.triggerEvent = triggerEvent;
        return this;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public Log setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
        return this;
    }
}
