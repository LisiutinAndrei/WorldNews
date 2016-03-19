package models.domain.orm;

import javax.persistence.*;

@Entity
@Table(name = "eventinstance")
public class EventInstance {

    @Id
    @Column(name = EVENT_INSTANCE_ID_COLUMN)
    private long _eventInstanceID;
    public static final String EVENT_INSTANCE_ID_COLUMN = "event_inst_id";

//    @Column(name = GEOINSTANCE_ID_COLUMN)
//    private long _geoInstanceID;
    public static final String GEOINSTANCE_ID_COLUMN = "gi_id";
//
//    @Column(name = TIMEINSTANCE_ID_COLUMN)
//    private long _timeInstanceID;
    public static final String TIMEINSTANCE_ID_COLUMN = "ti_id";

//    @Column(name = EVENT_ID_COLUMN)
//    private long _eventID;
    public static final String EVENT_ID_COLUMN = "e_id";


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = EventInstance.EVENT_ID_COLUMN)
    private Event _event = null;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = EventInstance.GEOINSTANCE_ID_COLUMN)
    private GeoInstance _geoInstance = null;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = EventInstance.TIMEINSTANCE_ID_COLUMN)
    private TimeInstance _timeInstance = null;

    public Event getEvent() {
        return _event;
    }

    public EventInstance setEvent(Event _event) {
        this._event = _event;
        return this;
    }

    public GeoInstance getGeoInstance() {
        return this._geoInstance;
    }

    public EventInstance setGeoInstance(GeoInstance geoInstance) {
        this._geoInstance = geoInstance;
        return this;
    }

    public TimeInstance getTimeInstance() {
        return this._timeInstance;
    }

    public EventInstance setTimeInstance(TimeInstance timeInstance) {
        this._timeInstance = timeInstance;
        return this;
    }

    public long getEventInstanceID() {
        return this._eventInstanceID;
    }

    public EventInstance setEventInstanceID(long eventInstanceID) {
        this._eventInstanceID = eventInstanceID;
        return this;
    }


//    public long getEventInstanceID() {
//        return this._eventInstanceID;
//    }
//
//    public EventInstance setEventInstanceID(long eventInstanceID) {
//        this._eventInstanceID = eventInstanceID;
//        return this;
//    }
//
//    public long getGeoInstanceID() {
//        return this._geoInstanceID;
//    }
//
//    public EventInstance setGeoInstanceID(long geoInstanceID) {
//        this._geoInstanceID = geoInstanceID;
//        return this;
//    }
//
//    public long getTimeInstanceID() {
//        return this._timeInstanceID;
//    }
//
//    public EventInstance setTimeInstanceID(long timeInstanceID) {
//        this._timeInstanceID = timeInstanceID;
//        return this;
//    }
//
//    public long getEventID() {
//        return this._eventID;
//    }
//
//    public EventInstance setEventID(long eventID) {
//        this._eventID = eventID;
//        return this;
//    }
}
