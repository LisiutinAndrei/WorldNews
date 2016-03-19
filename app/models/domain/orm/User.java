package models.domain.orm;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = User.USER_TABLE)
public class User {
    public static final String USER_TABLE = "worldnews_user";

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = USER_ID_COLUMN)
    private long _userID;
    public static final String USER_ID_COLUMN = "user_id";

    @Column(name = FULL_NAME_COLUMN)
    private String _fullName;
    public static final String FULL_NAME_COLUMN = "full_name";

    @Column(name = PASSWORD_COLUMN)
    private String _passwordEncrypted;
    public static final String PASSWORD_COLUMN = "password";

    @Column(name = CREATION_DATE_COLUMN)
    private Date _creationDate;
    public static final String CREATION_DATE_COLUMN = "creation_date";

    @Column(name = EMAIL_COLUMN)
    private String _email;
    public static final String EMAIL_COLUMN = "email";

    @Column(name = CONFIRMATION_TOKEN_COLUMN)
    private String _confirmationToken;
    public static final String CONFIRMATION_TOKEN_COLUMN = "confirmation_token";

    @Column(name = CONFIRMED_COLUMN)
    private boolean _confirmed;
    public static final String CONFIRMED_COLUMN = "is_confirmed";

    @OneToMany(fetch = FetchType.LAZY, mappedBy = UserEvent.USER_FIELD)
    private List<UserEvent> _userEvents = new ArrayList<UserEvent>();

    public long getUserID() {
        return _userID;
    }

    public User setUserID(long userID) {
        this._userID = userID;
        return this;
    }

    public String getFullName() {
        return this._fullName;
    }

    public User setFullName(String fullName) {
        this._fullName = fullName;
        return this;
    }

    public String getPasswordEncrypted() {
        return this._passwordEncrypted;
    }

    public User setPasswordEncrypted(String passwordHashed) {
        this._passwordEncrypted = passwordHashed;
        return this;
    }

    public Date getCreationDate() {
        return this._creationDate;
    }

    public User setCreationDate(Date creationDate) {
        this._creationDate = creationDate;
        return this;
    }

    public String getEmail() {
        return this._email;
    }

    public User setEmail(String email) {
        this._email = email;
        return this;
    }

    public String getConfirmationToken() {
        return this._confirmationToken;
    }

    public User setConfirmationToken(String confirmationToken) {
        this._confirmationToken = confirmationToken;
        return this;
    }

    public boolean isConfirmed() {
        return this._confirmed;
    }

    public User setConfirmed(boolean confirmed) {
        this._confirmed = confirmed;
        return this;
    }

    public List<UserEvent> getUserEvents() {
        return this._userEvents;
    }

    public User setUserEvents(List<UserEvent> userEvents) {
        this._userEvents = userEvents;
        return this;
    }
}
