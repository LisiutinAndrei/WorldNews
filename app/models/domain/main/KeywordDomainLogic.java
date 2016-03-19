package models.domain.main;

import models.domain.BaseDomainLogic;
import models.domain.orm.Actor;
import models.domain.orm.Event;
import models.domain.orm.Keyword;
import models.utils.infrastructurePackages.Tuple;
import models.view.main.keyword.KeywordEventsFilter;
import models.view.main.keyword.KeywordFilter;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class KeywordDomainLogic extends BaseDomainLogic {

    public KeywordDomainLogic() {
        super();
    }

    public Keyword getKeywordByFilter(KeywordEventsFilter filter) {
        Keyword actors = null;

        actors = this._runSqlAction((em) -> {
            return this._getKeywordByIDSql(em, filter);
        });

        return actors;
    }

    private Keyword _getKeywordByIDSql(EntityManager em, KeywordEventsFilter filter) {

        TypedQuery<Object[]> query = em.createQuery(
                "SELECT k, e, t, g, p, a " +
                        "FROM models.domain.orm.Keyword k " +
                        "LEFT JOIN k._events e " +
                        "LEFT JOIN e._geolocation g " +
                        "LEFT JOIN e._provenance p " +
                        "LEFT JOIN e._timelocation t " +
                        "LEFT JOIN e._actors a " +
                        "WHERE k._keywordID = :keywordID " +
                        (filter.startDate == null ? "" : " AND t._endDate >= :startDate ") +
                        (filter.endDate == null ? "" : " AND t._beginDate <= :endDate ")
                , Object[].class);
        query.setParameter("keywordID", filter.keywordID);
        if (filter.startDate != null) {
            query.setParameter("startDate", filter.startDate);
        }
        if (filter.endDate != null) {
            query.setParameter("endDate", filter.endDate);
        }
        List<Object[]> list = query
                .getResultList();
        if (list.size() < 1) {
            throw new RuntimeException("Keyword not found!");
        }

        Keyword keyword = (Keyword) (list.get(0)[0]);
        keyword.getEvents().clear();
        for (Object[] obj : list) {
            Event event = (Event) obj[1];
            event.getActors().size();
            event.getKeywords().size();
            event.getTimelocation();
            event.getGeolocation();
            event.getProvenance();
            long count = keyword.getEvents().stream().filter(p -> p.getEventID() == event.getEventID()).count();
            if (count == 0) {
                keyword.getEvents().add(event);
            }
        }
        keyword.getEvents().sort(new Event.EventTimeComparator());
        return keyword;
    }


    public Tuple<List<Keyword>, Integer> getKeywordsList(KeywordFilter filter) {
        Tuple<List<Keyword>, Integer> keywords = null;

        keywords = this._runSqlAction((em) -> {
            return this._getKeywordsListSql(em, filter);
        });

        return keywords;
    }

    private Tuple<List<Keyword>, Integer> _getKeywordsListSql(EntityManager em, KeywordFilter filter) {

        TypedQuery<Object[]> query = em.createQuery(
                "SELECT k " +
                        "FROM models.domain.orm.Keyword k " +
                        "LEFT JOIN k._events e " +
                        (filter.nameIsEmpty() ? "" : "WHERE LOWER(k._keyword) LIKE LOWER(:keyword) ") +
                        "GROUP BY k " +
                        "ORDER BY COUNT(e) DESC"
                , Object[].class);
        if (!filter.nameIsEmpty()) {
            query.setParameter("keyword", "%" + filter.name + "%");
        }
        List<Object[]> list = query
                .setFirstResult((filter.paging.page - 1) * filter.paging.itemsPerPage)
                .setMaxResults(filter.paging.itemsPerPage)
                .getResultList();

        List<Keyword> keywords = new ArrayList<Keyword>();
        for (Object keyword : list) {
            keywords.add((Keyword) keyword);
        }

        query = em.createQuery(
                "SELECT COUNT(k) " +
                        "FROM models.domain.orm.Keyword k " +
                        (filter.nameIsEmpty() ? "" : "WHERE LOWER(k._keyword) LIKE LOWER(:keyword) ")
                , Object[].class);
        if (!filter.nameIsEmpty()) {
            query.setParameter("keyword", "%" + filter.name + "%");
        }
        list = query
                .getResultList();

        int count = ((Long) (Object) list.get(0)).intValue();
        return new Tuple<List<Keyword>, Integer>(keywords, count);
    }
}
