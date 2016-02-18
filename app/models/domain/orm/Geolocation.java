package models.domain.orm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "georep")
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
}
