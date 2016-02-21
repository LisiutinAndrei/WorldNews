package models.domain.orm;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Polygon;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "georep_polygon")
@PrimaryKeyJoinColumn(name = Geolocation.GEOLOCATION_ID_COLUMN)
public class GeolocationPolygon extends Geolocation {
//
//    @Id
//    @Column(name = GEOLOCATION_ID_COLUMN)
//    private long _geolocationID;
//    public static final String GEOLOCATION_ID_COLUMN = "geo_id";

    @Column(name = LOCATION_COLUMN)
    @JsonIgnore
    private Polygon _location;
    public static final String LOCATION_COLUMN = "location";

//    public long getGeolocationID() {
//        return _geolocationID;
//    }
//
//    public GeolocationPolygon setGeolocationID(long geolocationID) {
//        this._geolocationID = geolocationID;
//        return this;
//    }

    @JsonIgnore
    public Polygon getLocation() {
        return this._location;
    }

    public GeolocationPolygon setLocation(Polygon location) {
        this._location = location;
        return this;
    }
//
    @Override
    public Coordinate getLocationPoint() {
        if (this._location == null) {
            return null;
        }
        return this._location.getCentroid().getCoordinate();
    }

    @Override
    public Coordinate[] getCoordinates() {
        if (this._location == null) {
            return null;
        }
        return this._location.getCoordinates();
    }
}
