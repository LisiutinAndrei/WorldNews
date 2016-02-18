package models.domain.orm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "timerep")
public class Timelocation {

    @Id
    @Column(name = TIMELOCATION_ID_COLUMN)
    private long _timelocationID;
    public static final String TIMELOCATION_ID_COLUMN = "time_id";

    @Column(name = BEGIN_DATE_COLUMN)
    private Date _beginDate;
    public static final String BEGIN_DATE_COLUMN = "begin_date";

    @Column(name = END_DATE_COLUMN)
    private Date _endDate;
    public static final String END_DATE_COLUMN = "end_date";

    public long getTimelocationID() {
        return _timelocationID;
    }

    public Timelocation setTimelocationID(long timelocationID) {
        this._timelocationID = timelocationID;
        return this;
    }

    public Date getBeginDate() {
        return this._beginDate;
    }

    public Timelocation setBeginDate(Date beginDate) {
        this._beginDate = beginDate;
        return this;
    }

    public Date getEndDate() {
        return this._endDate;
    }

    public Timelocation setEndDate(Date endDate) {
        this._endDate = endDate;
        return this;
    }
}
