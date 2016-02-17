package models.domain.orm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "timerep")
public class Timelocation {

    @Id
    @Column(name = TIMELOCATION_ID_COLUMN)
    private long _timelocationID;
    public static final String TIMELOCATION_ID_COLUMN = "time_id";

    public long getTimelocationID() {
        return _timelocationID;
    }

    public Timelocation setTimelocationID(long timelocationID) {
        this._timelocationID = timelocationID;
        return this;
    }
}
