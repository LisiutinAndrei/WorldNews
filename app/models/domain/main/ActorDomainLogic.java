package models.domain.main;

import models.domain.BaseDomainLogic;
import models.domain.orm.Actor;
import models.domain.orm.Event;
import models.utils.infrastructurePackages.Paging;
import models.utils.infrastructurePackages.Tuple;
import models.view.main.actor.ActorEventsFilter;
import models.view.main.actor.ActorFilter;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ActorDomainLogic extends BaseDomainLogic {

    public ActorDomainLogic() {
        super();
    }

    public Actor getActorByFilter(ActorEventsFilter filter) {
        Actor actors = null;

        actors = this._runSqlAction((em) -> {
            return this._getActorByFilterSql(em, filter);
        });

        return actors;
    }

    private Actor _getActorByFilterSql(EntityManager em, ActorEventsFilter filter) {
        TypedQuery<Object[]> query = em.createQuery(
                "SELECT e, t, g, p, a, k " +
                        "FROM models.domain.orm.Actor a " +
                        "LEFT JOIN a._events e " +
                        "LEFT JOIN e._geolocation g " +
                        "LEFT JOIN e._provenance p " +
                        "LEFT JOIN e._timelocation t " +
                        "LEFT JOIN e._keywords k " +
                        "WHERE a._actorID = :actorID " +
                        (filter.startDate == null ? "" : " AND t._endDate >= :startDate ") +
                        (filter.endDate == null ? "" : " AND t._beginDate <= :endDate ")
                , Object[].class);
        query.setParameter("actorID", filter.actorID);
        if (filter.startDate != null) {
            query.setParameter("startDate", filter.startDate);
        }
        if (filter.endDate != null) {
            query.setParameter("endDate", filter.endDate);
        }
        List<Object[]> list = query
                .getResultList();
        if (list.size() < 1) {
            throw new RuntimeException("Actor not found!");
        }

        Actor actor = (Actor) (list.get(0)[4]);
        actor.getEvents().clear();
        for (Object[] obj : list) {
            Event event = (Event) obj[0];
            event.getActors().size();
            event.getKeywords().size();
            event.getTimelocation();
            event.getGeolocation();
            event.getProvenance();
            long count = actor.getEvents().stream().filter(p -> p.getEventID() == event.getEventID()).count();
            if (count == 0) {
                actor.getEvents().add(event);
            }
        }
        actor.getEvents().sort(new Event.EventTimeComparator());
        return actor;
    }

    public Tuple<List<Actor>, Integer> getActorsList(ActorFilter filter) {
        Tuple<List<Actor>, Integer> actors = null;

        actors = this._runSqlAction((em) -> {
            return this._getActorsListSql(em, filter);
        });

        return actors;
    }

    private Tuple<List<Actor>, Integer> _getActorsListSql(EntityManager em, ActorFilter filter) {

        TypedQuery<Object[]> query = em.createQuery(
                "SELECT a " +
                        "FROM models.domain.orm.Actor a " +
                        "LEFT JOIN a._events e " +
                        (filter.nameIsEmpty() ? "" : "WHERE a._entityName LIKE :name ") +
                        "GROUP BY a " +
                        "ORDER BY COUNT(e) DESC"
                , Object[].class);
        if (!filter.nameIsEmpty()) {
            query.setParameter("name", "%" + filter.name + "%");
        }
        List<Object[]> list = query
                .setFirstResult((filter.paging.page - 1) * filter.paging.itemsPerPage)
                .setMaxResults(filter.paging.itemsPerPage)
                .getResultList();

        List<Actor> actors = new ArrayList<Actor>();
        for (Object actor : list) {
            actors.add((Actor) actor);
        }

        query = em.createQuery(
                "SELECT COUNT(a) " +
                        "FROM models.domain.orm.Actor a " +
                        (filter.nameIsEmpty() ? "" : "WHERE a._entityName LIKE :name ")
                , Object[].class);
        if (!filter.nameIsEmpty()) {
            query.setParameter("name", "%" + filter.name + "%");
        }
        list = query
                .getResultList();

        int count = ((Long) (Object) list.get(0)).intValue();
        return new Tuple<List<Actor>, Integer>(actors, count);
    }
}
