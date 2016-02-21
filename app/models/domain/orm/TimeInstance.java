package models.domain.orm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "timeinstance")
public class TimeInstance {

    @Id
    @Column(name = TIMEINSTANCE_ID_COLUMN)
    private long _timeInstanceID;
    public static final String TIMEINSTANCE_ID_COLUMN = "time_inst_id";

    @Column(name = TIME_COLUMN)
    private String _time;
    public static final String TIME_COLUMN = "timex";

    @Column(name = TIME_TYPE_COLUMN)
    private String _timeType;
    public static final String TIME_TYPE_COLUMN = "time_type";

    @Column(name = COVERED_TEXT_COLUMN)
    private String _coveredText;
    public static final String COVERED_TEXT_COLUMN = "covered_text_t";

    @Column(name = PROVENANCE_ID_COLUMN)
    private long _provenanceID;
    public static final String PROVENANCE_ID_COLUMN = "pv_id";

    @Column(name = TIMELOCATION_ID_COLUMN)
    private long _timelocationID;
    public static final String TIMELOCATION_ID_COLUMN = "t_id";


    public String getCoveredText() {
        return this._coveredText;
    }

    public TimeInstance setCoveredText(String coveredText) {
        this._coveredText = coveredText;
        return this;
    }

    public long getProvenanceID() {
        return this._provenanceID;
    }

    public TimeInstance setProvenanceID(long provenanceID) {
        this._provenanceID = provenanceID;
        return this;
    }

    public long getTimeInstanceID() {
        return this._timeInstanceID;
    }

    public TimeInstance setTimeInstanceID(long timeInstanceID) {
        this._timeInstanceID = timeInstanceID;
        return this;
    }

    public String getTime() {
        return this._time;
    }

    public TimeInstance setTime(String time) {
        this._time = time;
        return this;
    }

    public String getTimeType() {
        return this._timeType;
    }

    public TimeInstance setTimeType(String timeType) {
        this._timeType = timeType;
        return this;
    }

    public long getTimelocationID() {
        return this._timelocationID;
    }

    public TimeInstance setTimelocationID(long timelocationID) {
        this._timelocationID = timelocationID;
        return this;
    }
}
