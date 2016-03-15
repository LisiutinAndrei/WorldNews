package models.domain.orm;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Point;

import javax.persistence.*;

@Entity
@Table(name = "georep")
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name = "geo_type", discriminatorType = DiscriminatorType.STRING)
public class Geolocation {

    @Id
    @Column(name = GEOLOCATION_ID_COLUMN)
    protected long _geolocationID;
    public static final String GEOLOCATION_ID_COLUMN = "geo_id";

    @JsonIgnore
    @Column(name = LOCATION_COLUMN)
    protected Geometry _location;
    public static final String LOCATION_COLUMN = "location";

    public long getGeolocationID() {
        return _geolocationID;
    }

    public Geolocation setGeolocationID(long geolocationID) {
        this._geolocationID = geolocationID;
        return this;
    }

    @JsonIgnore
    public Geometry getLocation() {
        return this._location;
    }

    public Geolocation setLocation(Point location) {
        this._location = location;
        return this;
    }

    @JsonProperty(value = "centroid")
    public Coordinate getLocationPoint() {
        if (this._location == null) {
            return null;
        }
        return this._location.getCentroid().getCoordinate();
    }

    @JsonProperty(value = "coordinates")
    public Coordinate[] getCoordinates() {
        if (this._location == null) {
            return null;
        }
        return this._location.getCoordinates();
    }


}
