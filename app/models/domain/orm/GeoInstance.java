package models.domain.orm;

import javax.persistence.*;

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

//    @Column(name = PROVENANCE_ID_COLUMN)
//    private long _provenanceID;
    public static final String PROVENANCE_ID_COLUMN = "pv_id";

//    @Column(name = GEOLOCATION_ID_COLUMN)
//    private long _geolocationID;
    public static final String GEOLOCATION_ID_COLUMN = "g_id";


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = GeoInstance.GEOLOCATION_ID_COLUMN)
    private Geolocation _geolocation = null;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = GeoInstance.PROVENANCE_ID_COLUMN)
    private Provenance _provenance = null;

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

//    public long getProvenanceID() {
//        return this._provenanceID;
//    }
//
//    public GeoInstance setProvenanceID(long provenanceID) {
//        this._provenanceID = provenanceID;
//        return this;
//    }

    public Geolocation getGeolocation() {
        return this._geolocation;
    }

    public GeoInstance setGeolocation(Geolocation geolocation) {
        this._geolocation = geolocation;
        return this;
    }

    public Provenance getPprovenance() {
        return this._provenance;
    }

    public GeoInstance setProvenance(Provenance provenance) {
        this._provenance = provenance;
        return this;
    }

//    public long getGeolocationID() {
//        return this._geolocationID;
//    }
//
//    public GeoInstance setGeolocationID(long geolocationID) {
//        this._geolocationID = geolocationID;
//        return this;
//    }
}
