package com.room.authentication.entity.postgres;

import jakarta.persistence.*;


@Entity
@Table(name = "driver")
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(unique = true, nullable = false)
    public String driverId;

    @Column( nullable = false)
    public boolean chargeAllowed;

    public Driver(String driverId , boolean chargeAllowed) {
        this.driverId = driverId;
        this.chargeAllowed = chargeAllowed;
    }

    public Driver() {

    }
}
