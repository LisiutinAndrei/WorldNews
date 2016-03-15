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

public class MapDomainLogic extends BaseDomainLogic {

    public MapDomainLogic() {
        super();
    }

    public List<Event> getEvents(MapFilterRequest filter) {
        List<Event> events = new ArrayList<Event>();

        events = this._runSqlAction((em) -> {
            return this._getEventsSql(em, filter);
        });

        return events;
    }

    private List<Event> _getEventsSql(EntityManager em, MapFilterRequest filter) {
        GeometryFactory geometryFactory = new GeometryFactory();
        Coordinate[] coords = new Coordinate[5];
        coords[0] = new Coordinate(filter.minLatitude, filter.minLongtitude);
        coords[1] = new Coordinate(filter.minLatitude, filter.maxLongtitude);
        coords[2] = new Coordinate(filter.maxLatitude, filter.maxLongtitude);
        coords[3] = new Coordinate(filter.maxLatitude, filter.minLongtitude);
        coords[4] = new Coordinate(filter.minLatitude, filter.minLongtitude);
        Polygon polygon = geometryFactory.createPolygon(coords);

        String polygon1 = String.format(Locale.ENGLISH,
                "ST_GeomFromText('POLYGON((%.5f %.5f, %.5f %.5f, %.5f %.5f, %.5f %.5f, %.5f %.5f))')",
                filter.minLatitude, filter.minLongtitude,
                filter.minLatitude, filter.maxLongtitude,
                filter.maxLatitude, filter.maxLongtitude,
                filter.maxLatitude, filter.minLongtitude,
                filter.minLatitude, filter.minLongtitude
        );

        TypedQuery<Object[]> query = em.createQuery(
                "SELECT e, t, g, p, (SELECT COUNT(ei) FROM e._eventInstances ei) AS e_count " +
                        "FROM models.domain.orm.Event e " +
                        "INNER JOIN e._timelocation t " +
                        "INNER JOIN e._geolocation g " +
                        "INNER JOIN e._provenance p " +
                        "WHERE intersects(g._location, :polygon) = true " +
                        (filter.startDate == null ? "" : " AND t._endDate >= :startDate ") +
                        (filter.endDate == null ? "" : " AND t._beginDate <= :endDate ") +
                        "ORDER BY e_count DESC"
                , Object[].class);
        query.setParameter("polygon", polygon);
        if (filter.startDate != null) {
            query.setParameter("startDate", filter.startDate);
        }
        if (filter.endDate != null) {
            query.setParameter("endDate", filter.endDate);
        }
        List<Object[]> list = query.setFirstResult(0)
                .setMaxResults(10)
                .getResultList();

        List<Event> events = new ArrayList<Event>();
        for (Object[] e : list) {
            Event event = (Event) (e[0]);
            event.setGeolocation(new CentredGeolocation(event.getGeolocation(), polygon));
            events.add(event);
        }
        return events;
    }
}
