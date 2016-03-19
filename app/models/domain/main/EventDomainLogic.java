package models.domain.main;

import models.domain.BaseDomainLogic;
import models.domain.orm.Event;
import models.domain.orm.EventInstance;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

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
                        "INNER JOIN e._eventInstances ei " +
                        "WHERE e._eventID = :eventID"
                , Object[].class);
        query.setParameter("eventID", eventID);
        List<Object[]> list = query
                .getResultList();
        if (list.size() < 1) {
            throw new RuntimeException("Event not found!");
        }

        Event event = (Event) (list.get(0)[0]);
        event.getActors().size();
        event.getKeywords().size();
        event.getTimelocation();
        event.getGeolocation();
        event.getProvenance();
        event.getEventInstances().size();

        return event;
    }

    public EventInstance getEventinstanceByID(long eventInstanceID) {
        EventInstance events = null;

        events = this._runSqlAction((em) -> {
            return this._getEventinstanceByIDSql(em, eventInstanceID);
        });

        return events;
    }

    private EventInstance _getEventinstanceByIDSql(EntityManager em, long eventInstanceID) {

        TypedQuery<Object[]> query = em.createQuery(
                "SELECT ei, e, gi, ti, g, t, tip, gip " +
                        "FROM models.domain.orm.EventInstance ei " +
                        "INNER JOIN ei._event e " +
                        "INNER JOIN ei._geoInstance gi " +
                        "INNER JOIN ei._timeInstance ti " +
                        "INNER JOIN gi._geolocation g " +
                        "INNER JOIN ti._timelocation t " +
                        "INNER JOIN ti._provenance tip " +
                        "INNER JOIN gi._provenance gip " +
                        "WHERE ei._eventInstanceID = :eventInstanceID"
                , Object[].class);
        query.setParameter("eventInstanceID", eventInstanceID);
        List<Object[]> list = query
                .getResultList();
        if (list.size() < 1) {
            throw new RuntimeException("Event instance not found!");
        }

        EventInstance event = (EventInstance) (list.get(0)[0]);
        event.getEvent();
        event.getGeoInstance().getGeolocation();
        event.getTimeInstance().getTimelocation();
        return event;
    }
}
