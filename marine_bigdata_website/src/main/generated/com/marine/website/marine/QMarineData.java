package com.marine.website.marine;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMarineData is a Querydsl query type for MarineData
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMarineData extends EntityPathBase<MarineData> {

    private static final long serialVersionUID = 712686438L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMarineData marineData = new QMarineData("marineData");

    public final StringPath before_port = createString("before_port");

    public final StringPath callsign = createString("callsign");

    public final StringPath crew = createString("crew");

    public final DateTimePath<java.time.LocalDateTime> departure = createDateTime("departure", java.time.LocalDateTime.class);

    public final StringPath departureCount = createString("departureCount");

    public final StringPath division = createString("division");

    public final DateTimePath<java.time.LocalDateTime> entry_Datetime = createDateTime("entry_Datetime", java.time.LocalDateTime.class);

    public final StringPath entryCount = createString("entryCount");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.time.LocalDateTime> localDateTime = createDateTime("localDateTime", java.time.LocalDateTime.class);

    public final StringPath moorint_site = createString("moorint_site");

    public final StringPath nationality = createString("nationality");

    public final StringPath next_port = createString("next_port");

    public final StringPath out_int_ward_port = createString("out_int_ward_port");

    public final StringPath portname = createString("portname");

    public final StringPath preliminary = createString("preliminary");

    public final StringPath sail = createString("sail");

    public final QShip ship;

    public final StringPath ship_use = createString("ship_use");

    public final StringPath shipname = createString("shipname");

    public final StringPath sortation = createString("sortation");

    public final StringPath total_ton = createString("total_ton");

    public QMarineData(String variable) {
        this(MarineData.class, forVariable(variable), INITS);
    }

    public QMarineData(Path<? extends MarineData> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMarineData(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMarineData(PathMetadata metadata, PathInits inits) {
        this(MarineData.class, metadata, inits);
    }

    public QMarineData(Class<? extends MarineData> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.ship = inits.isInitialized("ship") ? new QShip(forProperty("ship"), inits.get("ship")) : null;
    }

}

