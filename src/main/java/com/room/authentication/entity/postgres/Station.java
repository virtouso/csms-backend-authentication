package com.room.authentication.entity.postgres;

import jakarta.persistence.*;


@Entity
@Table(name = "station")
public class Station {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    public String stationId;

    public Station(String stationId) {
        this.stationId = stationId;
    }


    public Station() {

    }
}
