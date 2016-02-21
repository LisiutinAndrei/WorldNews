package models.domain.orm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "eventinstance")
public class EventInstance {

    @Id
    @Column(name = EVENT_INSTANCE_ID_COLUMN)
    private long _eventInstanceID;
    public static final String EVENT_INSTANCE_ID_COLUMN = "event_inst_id";

    @Column(name = GEOINSTANCE_ID_COLUMN)
    private long _geoInstanceID;
    public static final String GEOINSTANCE_ID_COLUMN = "gi_id";

    @Column(name = TIMEINSTANCE_ID_COLUMN)
    private long _timeInstanceID;
    public static final String TIMEINSTANCE_ID_COLUMN = "ti_id";

    @Column(name = EVENT_ID_COLUMN)
    private long _eventID;
    public static final String EVENT_ID_COLUMN = "e_id";

    public long getEventInstanceID() {
        return this._eventInstanceID;
    }

    public EventInstance setEventInstanceID(long eventInstanceID) {
        this._eventInstanceID = eventInstanceID;
        return this;
    }

    public long getGeoInstanceID() {
        return this._geoInstanceID;
    }

    public EventInstance setGeoInstanceID(long geoInstanceID) {
        this._geoInstanceID = geoInstanceID;
        return this;
    }

    public long getTimeInstanceID() {
        return this._timeInstanceID;
    }

    public EventInstance setTimeInstanceID(long timeInstanceID) {
        this._timeInstanceID = timeInstanceID;
        return this;
    }

    public long getEventID() {
        return this._eventID;
    }

    public EventInstance setEventID(long eventID) {
        this._eventID = eventID;
        return this;
    }
}
