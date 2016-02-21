package models.domain.orm;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vividsolutions.jts.geom.Coordinate;

import javax.persistence.*;

@Entity
@Table(name = "georep")
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Geolocation {

    @Id
    @Column(name = GEOLOCATION_ID_COLUMN)
    private long _geolocationID;
    public static final String GEOLOCATION_ID_COLUMN = "geo_id";

    public long getGeolocationID() {
        return _geolocationID;
    }

    public Geolocation setGeolocationID(long geolocationID) {
        this._geolocationID = geolocationID;
        return this;
    }

    @JsonProperty(value = "centroid")
    public abstract Coordinate getLocationPoint();

    @JsonProperty(value = "coordinates")
    public abstract Coordinate[] getCoordinates();
}
