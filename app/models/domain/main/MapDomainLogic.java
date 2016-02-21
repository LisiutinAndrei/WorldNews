package models.domain.main;

import models.domain.BaseDomainLogic;
import models.domain.orm.Event;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class MapDomainLogic extends BaseDomainLogic {

    public MapDomainLogic() {
        super();
    }

    public List<Event> getEvents() {
        List<Event> events = new ArrayList<Event>();

        events = this._runSqlAction((em) -> {
            return this._getEventsSql(em);
        });

        return events;
    }

    private List<Event> _getEventsSql(EntityManager em) {
        List<Object[]> list = em.createQuery("SELECT e, t, g, p, (SELECT COUNT(ei) FROM e._eventInstances ei) AS e_count " +
                "FROM models.domain.orm.Event e " +
                "INNER JOIN e._timelocation t " +
                "INNER JOIN e._geolocation g " +
                "INNER JOIN e._provenance p " +
                "ORDER BY e_count DESC"
        , Object[].class)
                .setFirstResult(0)
                .setMaxResults(10)
                .getResultList();

        List<Event> events = new ArrayList<Event>();
        list.forEach(e -> events.add((Event)(e[0])));
        return events;
    }
}
