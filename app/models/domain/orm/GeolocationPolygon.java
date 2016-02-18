package models.domain.orm;

import com.vividsolutions.jts.geom.Polygon;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "georep_polygon")
public class GeolocationPolygon {

    @Id
    @Column(name = GEOLOCATION_ID_COLUMN)
    private long _geolocationID;
    public static final String GEOLOCATION_ID_COLUMN = "geo_id";

    @Column(name = LOCATION_COLUMN)
    private Polygon _location;
    public static final String LOCATION_COLUMN = "location";

    public long getGeolocationID() {
        return _geolocationID;
    }

    public GeolocationPolygon setGeolocationID(long geolocationID) {
        this._geolocationID = geolocationID;
        return this;
    }

    public Polygon get_location() {
        return this._location;
    }

    public GeolocationPolygon set_location(Polygon location) {
        this._location = location;
        return this;
    }
}
