package models.domain.orm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "keyword")
public class Keyword {
    public static final String EVENT_KEYWORD_KEYWORD_ID = "k_id";

    @Id
    @Column(name = KEYWORD_ID_COLUMN)
    private long _keywordID;
    public static final String KEYWORD_ID_COLUMN = "keyword_id";

    @Column(name = KEYWORD_COLUMN)
    private String _keyword;
    public static final String KEYWORD_COLUMN = "keyword";

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
}
