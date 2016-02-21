package models.domain.orm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "geoinstance")
public class GeoInstance {

    @Id
    @Column(name = GEOINSTANCE_ID_COLUMN)
    private long _geoInstanceID;
    public static final String GEOINSTANCE_ID_COLUMN = "geo_inst_id";

    @Column(name = LOCATION_COLUMN)
    private String _location;
    public static final String LOCATION_COLUMN = "loc";

    @Column(name = LOCATION_TYPE_COLUMN)
    private String _locationType;
    public static final String LOCATION_TYPE_COLUMN = "loc_type";

    @Column(name = COVERED_TEXT_COLUMN)
    private String _coveredText;
    public static final String COVERED_TEXT_COLUMN = "covered_text_g";

    @Column(name = PROVENANCE_ID_COLUMN)
    private long _provenanceID;
    public static final String PROVENANCE_ID_COLUMN = "pv_id";

    @Column(name = GEOLOCATION_ID_COLUMN)
    private long _geolocationID;
    public static final String GEOLOCATION_ID_COLUMN = "g_id";


    public long getGeoInstanceID() {
        return this._geoInstanceID;
    }

    public GeoInstance setGeoInstanceID(long geoInstanceID) {
        this._geoInstanceID = geoInstanceID;
        return this;
    }

    public String getLocation() {
        return this._location;
    }

    public GeoInstance setLocation(String location) {
        this._location = location;
        return this;
    }

    public String getLocationType() {
        return this._locationType;
    }

    public GeoInstance setLocationType(String locationType) {
        this._locationType = locationType;
        return this;
    }

    public String getCoveredText() {
        return this._coveredText;
    }

    public GeoInstance setCoveredText(String coveredText) {
        this._coveredText = coveredText;
        return this;
    }

    public long getProvenanceID() {
        return this._provenanceID;
    }

    public GeoInstance setProvenanceID(long provenanceID) {
        this._provenanceID = provenanceID;
        return this;
    }

    public long getGeolocationID() {
        return this._geolocationID;
    }

    public GeoInstance setGeolocationID(long geolocationID) {
        this._geolocationID = geolocationID;
        return this;
    }
}
