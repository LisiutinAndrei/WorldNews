package models.domain.orm;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.Comparator;
import java.util.Date;

@Entity
@Table(name = "user_event")
@JsonIdentityInfo(scope=UserEvent.class, generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "_id")
public class UserEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = USER_EVENT_ID_COLUMN)
    private long _userEventID;
    public static final String USER_EVENT_ID_COLUMN = "user_event_id";

    @Column(name = DATE_COLUMN)
    private Date _date;
    public static final String DATE_COLUMN = "date";

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = UserEvent.USER_ID_COLUMN)
    private User _user;
    public static final String USER_ID_COLUMN = "user_id";
    public static final String USER_FIELD = "_user";

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = UserEvent.EVENT_ID_COLUMN)
    private Event _event;
    public static final String EVENT_ID_COLUMN = "event_id";

    public Date getDate() {
        return _date;
    }

    public UserEvent setDate(Date date) {
        this._date = date;
        return this;
    }

    public User getUser() {
        return _user;
    }

    public UserEvent setUser(User user) {
        this._user = user;
        return this;
    }

    public Event getEvent() {
        return _event;
    }

    public UserEvent setEvent(Event event) {
        this._event = event;
        return this;
    }

    public long getUserEventID() {
        return this._userEventID;
    }

    public UserEvent setUserEventID(long _userEventID) {
        this._userEventID = _userEventID;
        return this;
    }

    public static class EventTimeComparator implements Comparator<UserEvent> {
        @Override
        public int compare(UserEvent o1, UserEvent o2) {
            return o2.getDate().compareTo(o1.getDate());
        }
    }
}
