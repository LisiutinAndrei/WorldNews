package models.domain.main;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Polygon;
import models.domain.BaseDomainLogic;
import models.domain.orm.Event;
import models.view.main.map.CentredGeolocation;
import models.view.main.map.MapFilterRequest;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class EventDomainLogic extends BaseDomainLogic {

    public EventDomainLogic() {
        super();
    }

    public Event getEventByID(long eventID) {
        Event events = null;

        events = this._runSqlAction((em) -> {
            return this._getEventByIDSql(em, eventID);
        });

        return events;
    }

    private Event _getEventByIDSql(EntityManager em, long eventID) {

        TypedQuery<Object[]> query = em.createQuery(
                "SELECT e, t, g, p, a, k " +
                        "FROM models.domain.orm.Event e " +
                        "INNER JOIN e._timelocation t " +
                        "INNER JOIN e._geolocation g " +
                        "INNER JOIN e._provenance p " +
                        "INNER JOIN e._actors a " +
                        "INNER JOIN e._keywords k " +
                        "WHERE e._eventID = :eventID"
                , Object[].class);
        query.setParameter("eventID", eventID);
        List<Object[]> list = query
                .getResultList();
        if (list.size() < 1) {
            throw new RuntimeException("Event not found!");
        }

        Event event =(Event) (list.get(0)[0]);
        event.getActors().size();
        event.getKeywords().size();
        event.getTimelocation();
        event.getGeolocation();
        event.getProvenance();

        return event;
    }
}
