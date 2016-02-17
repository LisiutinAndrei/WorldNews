package models.domain.orm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "event")
public class Event {

    @Id
    @Column(name = EVENT_ID_COLUMN)
    private long _eventID;
    public static final String EVENT_ID_COLUMN = "event_id";

    @Column(name = GEOLOCATION_ID_COLUMN)
    private long _geolocationID;
    public static final String GEOLOCATION_ID_COLUMN = "g_id";

    @Column(name = TIMELOCATION_ID_COLUMN)
    private long _timelocationID;
    public static final String TIMELOCATION_ID_COLUMN = "t_id";

    @Column(name = PROVENANCE_ID_COLUMN)
    private long _provenanceID;
    public static final String PROVENANCE_ID_COLUMN = "infpv_id";


    public long getEventID() {
        return _eventID;
    }

    public Event setEventID(long eventID) {
        this._eventID = eventID;
        return this;
    }

    public long getTimelocationID() {
        return _timelocationID;
    }

    public Event setTimelocationID(long timelocationID) {
        this._timelocationID = timelocationID;
        return this;
    }

    public long getGeolocationID() {
        return _geolocationID;
    }

    public Event setGeolocationID(long geolocationID) {
        this._geolocationID = geolocationID;
        return this;
    }

    public long getProvenanceID() {
        return _provenanceID;
    }

    public Event setProvenanceID(long provenanceID) {
        this._provenanceID = provenanceID;
        return this;
    }
}
