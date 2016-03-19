package models.domain.orm;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "actor")
@JsonIdentityInfo(scope=Actor.class, generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "_id")
public class Actor {
    public static final String EVENT_ACTOR_ACTOR_ID = "a_id";

    @Id
    @Column(name = ACTOR_ID_COLUMN)
    private long _actorID;
    public static final String ACTOR_ID_COLUMN = "actor_id";

    @Column(name = ENTITY_NAME_COLUMN)
    private String _entityName;
    public static final String ENTITY_NAME_COLUMN = "entity_name";

    @Column(name = ENTITY_TYPE_COLUMN)
    private String _entityType;
    public static final String ENTITY_TYPE_COLUMN = "entity_type";

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = Event.ACTORS_MANYTOMANY_FIELD)
    private List<Event> _events = new ArrayList<Event>();



    public long getActorID() {
        return _actorID;
    }

    public Actor setActorID(long actorID) {
        this._actorID = actorID;
        return this;
    }

    public String getEntityName() {
        return _entityName;
    }

    public Actor setEntityName(String entityName) {
        this._entityName = entityName;
        return this;
    }

    public String getEntityType() {
        return _entityType;
    }

    public Actor setEntityType(String entityType) {
        this._entityType = entityType;
        return this;
    }

    public List<Event> getEvents() {
        return this._events;
    }

    public Actor setEvents(List<Event> events) {
        this._events = events;
        return this;
    }
}
