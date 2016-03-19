package models.domain.orm;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "keyword")
@JsonIdentityInfo(scope = Actor.class, generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "_id")
public class Keyword {
    public static final String EVENT_KEYWORD_KEYWORD_ID = "k_id";

    @Id
    @Column(name = KEYWORD_ID_COLUMN)
    private long _keywordID;
    public static final String KEYWORD_ID_COLUMN = "keyword_id";

    @Column(name = KEYWORD_COLUMN)
    private String _keyword;
    public static final String KEYWORD_COLUMN = "keyword";

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = Event.KEYWORDS_MANYTOMANY_FIELD)
    private List<Event> _events = new ArrayList<Event>();


    public long getKeywordID() {
        return this._keywordID;
    }

    public Keyword setKeywordID(long keywordID) {
        this._keywordID = keywordID;
        return this;
    }

    public String getKeyword() {
        return this._keyword;
    }

    public Keyword setKeyword(String keyword) {
        this._keyword = keyword;
        return this;
    }

    public List<Event> getEvents() {
        return this._events;
    }

    public Keyword setEvents(List<Event> events) {
        this._events = events;
        return this;
    }
}
