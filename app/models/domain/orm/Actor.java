package models.domain.orm;

import javax.persistence.*;

@Entity
@Table(name = "actor")
public class Actor {

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
}
