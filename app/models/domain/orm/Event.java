package models.domain.orm;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = Event.EVENT_TABLE)
public class Event implements Serializable {
    public static final String EVENT_TABLE = "event";
    public static final String EVENT_ACTOR_TABLE = "eventactor";
    public static final String EVENT_ACTOR_EVENT_ID = "e_id";
    public static final String EVENT_KEYWORD_TABLE = "eventkeyword";
    public static final String EVENT_KEYWORD_EVENT_ID = "e_id";

    @Id
    @Column(name = EVENT_ID_COLUMN)
    private long _eventID;
    public static final String EVENT_ID_COLUMN = "event_id";

    //    @Column(name = GEOLOCATION_ID_COLUMN)
//    private long _geolocationID;
    public static final String GEOLOCATION_ID_COLUMN = "g_id";
    //
//    @Column(name = TIMELOCATION_ID_COLUMN)
//    private long _timelocationID;
    public static final String TIMELOCATION_ID_COLUMN = "t_id";
    //
//    @Column(name = PROVENANCE_ID_COLUMN)
//    private long _provenanceID;
    public static final String PROVENANCE_ID_COLUMN = "infpv_id";

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = EventInstance.EVENT_ID_COLUMN)
    private List<EventInstance> _eventInstances = new ArrayList<EventInstance>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = Event.TIMELOCATION_ID_COLUMN)
    private Timelocation _timelocation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = Event.GEOLOCATION_ID_COLUMN)
    private Geolocation _geolocation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = Event.PROVENANCE_ID_COLUMN)
    private Provenance _provenance;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = Event.EVENT_KEYWORD_TABLE, joinColumns = @JoinColumn(name = Event.EVENT_KEYWORD_EVENT_ID), inverseJoinColumns = @JoinColumn(name = Keyword.EVENT_KEYWORD_KEYWORD_ID))
    private List<Keyword> _keywords = new ArrayList<Keyword>();

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = Event.EVENT_ACTOR_TABLE, joinColumns = @JoinColumn(name = Event.EVENT_ACTOR_EVENT_ID), inverseJoinColumns = @JoinColumn(name = Actor.EVENT_ACTOR_ACTOR_ID))
    private List<Actor> _actors = new ArrayList<Actor>();

    public long getEventID() {
        return _eventID;
    }

    public Event setEventID(long eventID) {
        this._eventID = eventID;
        return this;
    }
//
//    public long getTimelocationID() {
//        return _timelocationID;
//    }
//
//    public Event setTimelocationID(long timelocationID) {
//        this._timelocationID = timelocationID;
//        return this;
//    }
//
//    public long getGeolocationID() {
//        return _geolocationID;
//    }
//
//    public Event setGeolocationID(long geolocationID) {
//        this._geolocationID = geolocationID;
//        return this;
//    }
//
//    public long getProvenanceID() {
//        return _provenanceID;
//    }
//
//    public Event setProvenanceID(long provenanceID) {
//        this._provenanceID = provenanceID;
//        return this;
//    }

    public List<EventInstance> getEventInstances() {
        return this._eventInstances;
    }

    public Event setEventInstances(List<EventInstance> eventInstances) {
        this._eventInstances = eventInstances;
        return this;
    }

    public Timelocation getTimelocation() {
        return this._timelocation;
    }

    public Event setTimelocation(Timelocation timelocation) {
        this._timelocation = timelocation;
        return this;
    }

    public Geolocation getGeolocation() {
        return this._geolocation;
    }

    public Event setGeolocation(Geolocation geolocation) {
        this._geolocation = geolocation;
        return this;
    }

    public Provenance getProvenance() {
        return this._provenance;
    }

    public Event setProvenance(Provenance provenance) {
        this._provenance = provenance;
        return this;
    }

    public List<Keyword> getKeywords() {
        return this._keywords;
    }

    public Event setKeywords(List<Keyword> keywords) {
        this._keywords = keywords;
        return this;
    }

    public List<Actor> getActors() {
        return this._actors;
    }

    public Event setActors(List<Actor> actors) {
        this._actors = actors;
        return this;
    }
}
