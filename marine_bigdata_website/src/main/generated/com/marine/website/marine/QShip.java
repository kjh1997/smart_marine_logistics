package com.marine.website.marine;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QShip is a Querydsl query type for Ship
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QShip extends EntityPathBase<Ship> {

    private static final long serialVersionUID = -81264490L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QShip ship1 = new QShip("ship1");

    public final StringPath CallSign = createString("CallSign");

    public final StringPath Company = createString("Company");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imo = createString("imo");

    public final QMarineTraffic marineTraffic;

    public final QMarineData ship;

    public QShip(String variable) {
        this(Ship.class, forVariable(variable), INITS);
    }

    public QShip(Path<? extends Ship> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QShip(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QShip(PathMetadata metadata, PathInits inits) {
        this(Ship.class, metadata, inits);
    }

    public QShip(Class<? extends Ship> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.marineTraffic = inits.isInitialized("marineTraffic") ? new QMarineTraffic(forProperty("marineTraffic"), inits.get("marineTraffic")) : null;
        this.ship = inits.isInitialized("ship") ? new QMarineData(forProperty("ship"), inits.get("ship")) : null;
    }

}

