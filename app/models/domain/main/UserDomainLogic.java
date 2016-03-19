package models.domain.main;

import models.domain.BaseDomainLogic;
import models.domain.orm.Actor;
import models.domain.orm.Event;
import models.domain.orm.User;
import models.domain.orm.UserEvent;
import models.utils.infrastructurePackages.Tuple;
import models.view.main.actor.ActorFilter;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class UserDomainLogic extends BaseDomainLogic {

    public UserDomainLogic() {
        super();
    }

    public User getUserWithEventsByID(long userID) {
        User user = null;

        user = this._runSqlAction((em) -> {
            return this._getUserWithEventsByIDSql(em, userID);
        });

        return user;
    }

    private User _getUserWithEventsByIDSql(EntityManager em, long userID) {
        TypedQuery<Object[]> query = em.createQuery(
                "SELECT ue, u, e " +
                        "FROM models.domain.orm.UserEvent ue " +
                        "INNER JOIN ue._user u " +
                        "INNER JOIN ue._event e " +
                        "WHERE u._userID = :userID " +
                        "ORDER BY ue._date DESC "
                , Object[].class);
        query.setParameter("userID", userID);
        List<Object[]> list = query
                .setMaxResults(10)
                .getResultList();

        List<UserEvent> userEvents = new ArrayList<UserEvent>();
        User user = null;
        for(Object[] ue: list) {
            userEvents.add((UserEvent)ue[0]);
            user = (User)ue[1];
        }
        userEvents.sort(new UserEvent.EventTimeComparator());

        if (user == null) {
            TypedQuery<User> userQuery = em.createQuery(
                    "SELECT u " +
                            "FROM models.domain.orm.User u " +
                            "WHERE u._userID = :userID"
                    , User.class);
            userQuery.setParameter("userID", userID);
            List<User> userList = userQuery.getResultList();
            if (userList.size() < 1) {
                throw new RuntimeException("User not found!");
            }
            user = userList.get(0);
        }

        user.setUserEvents(userEvents);
        return user;
    }

    public UserEvent saveUserEvent(UserEvent obj) {
        UserEvent userEvent = null;
        userEvent = this._runSqlAction((em) -> {
            return this._saveUserEventSql(em, obj);
        });

        return userEvent;
    }

    private UserEvent _saveUserEventSql(EntityManager em, UserEvent obj) {
        em.persist(obj);
        return obj;
    }
}
