package com.marine.website.marine;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMarineTraffic is a Querydsl query type for MarineTraffic
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMarineTraffic extends EntityPathBase<MarineTraffic> {

    private static final long serialVersionUID = -892080959L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMarineTraffic marineTraffic = new QMarineTraffic("marineTraffic");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> lat = createNumber("lat", Long.class);

    public final NumberPath<Long> lon = createNumber("lon", Long.class);

    public final QShip mmsi;

    public final DateTimePath<java.time.LocalDateTime> timestamp = createDateTime("timestamp", java.time.LocalDateTime.class);

    public QMarineTraffic(String variable) {
        this(MarineTraffic.class, forVariable(variable), INITS);
    }

    public QMarineTraffic(Path<? extends MarineTraffic> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMarineTraffic(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMarineTraffic(PathMetadata metadata, PathInits inits) {
        this(MarineTraffic.class, metadata, inits);
    }

    public QMarineTraffic(Class<? extends MarineTraffic> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.mmsi = inits.isInitialized("mmsi") ? new QShip(forProperty("mmsi"), inits.get("mmsi")) : null;
    }

}

