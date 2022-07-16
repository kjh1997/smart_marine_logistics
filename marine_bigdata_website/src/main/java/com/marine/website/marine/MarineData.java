package com.marine.website.marine;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class MarineData {
    @Id
    @Column(name = "data_id")
    private Long id;
    private String portname;
    private String callsign;

    private String shipname;

    @OneToOne
    @JoinColumn(name="ship_id")
    private Ship ship;

    private String entryCount;
    private String departureCount;
    private String division;
    private String sortation;
    private String out_int_ward_port;
    private String total_ton;
    private LocalDateTime entry_Datetime;
    private LocalDateTime departure;
    private String nationality;
    private String moorint_site;
    private String next_port;
    private String before_port;
    private String ship_use;
    private String crew;
    private String preliminary;
    private String sail;

    private LocalDateTime localDateTime;
}

