package models.domain.orm;

import com.vividsolutions.jts.geom.Point;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "georep_point")
public class GeolocationPoint {

    @Id
    @Column(name = GEOLOCATION_ID_COLUMN)
    private long _geolocationID;
    public static final String GEOLOCATION_ID_COLUMN = "geo_id";

    @Column(name = LOCATION_COLUMN)
    private Point _location;
    public static final String LOCATION_COLUMN = "location";

    public long getGeolocationID() {
        return _geolocationID;
    }

    public GeolocationPoint setGeolocationID(long geolocationID) {
        this._geolocationID = geolocationID;
        return this;
    }

    public Point get_location() {
        return this._location;
    }

    public GeolocationPoint set_location(Point location) {
        this._location = location;
        return this;
    }
}
