package com.marine.website.marine;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class MarineTraffic {
    @Id
    @GeneratedValue
    @Column(name = "traffic_id")
    private Long id;


    private Long lat;
    private Long lon;

    private LocalDateTime timestamp;
    @OneToOne(mappedBy = "marineTraffic")
    private Ship mmsi;


}
