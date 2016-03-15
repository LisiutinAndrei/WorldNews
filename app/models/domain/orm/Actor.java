package models.domain.orm;

import javax.persistence.*;

@Entity
@Table(name = "actor")
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
}
