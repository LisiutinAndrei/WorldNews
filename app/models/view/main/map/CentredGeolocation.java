package models.view.main.map;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import models.domain.orm.Geolocation;

public class CentredGeolocation extends Geolocation {
    public CentredGeolocation(Geolocation geolocation, Geometry mapBorders) {
        this.mapBorders = mapBorders;
        this._geolocationID = geolocation.getGeolocationID();
        this._location = geolocation.getLocation();
    }

    protected Geometry mapBorders;

    @Override
    public Coordinate getLocationPoint() {
        if (this.mapBorders == null || this._location == null) {
            return super.getLocationPoint();
        }

        if (this.mapBorders.intersects(this._location.getCentroid())) {
            return super.getLocationPoint();
        }
        return this.mapBorders.intersection(this._location).getCentroid().getCoordinate();
    }
}
