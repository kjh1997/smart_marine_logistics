package com.marine.website.marine;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Ship {
    @Id
    @Column(name = "ship_id")
    @GeneratedValue
    private Long id;


    private String imo;

    @OneToOne(mappedBy = "ship")
    private MarineData ship;
    private String Company;
    private String CallSign;

    @OneToOne
    @JoinColumn(name = "traffic_id")
    private MarineTraffic marineTraffic;

}
