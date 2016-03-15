package models.business.main;

import models.business.BaseBusinessLogic;
import models.domain.main.MapDomainLogic;
import models.domain.orm.Event;
import models.utils.infrastructurePackages.accountSession.IAccountSession;
import models.utils.infrastructurePackages.request.IRequestPackage;
import models.utils.infrastructurePackages.response.IResponsePackage;
import models.view.main.map.MapFilterRequest;

import java.util.List;

public class MapBusinessLogic extends BaseBusinessLogic {
    public MapBusinessLogic() {
        super();
    }

    public IResponsePackage<List<Event>> getEvents(IRequestPackage<MapFilterRequest> request) {
        IAccountSession account = request.getAccountSession();
        MapFilterRequest filter = request.getRequestData();
       List<Event> events = new MapDomainLogic().getEvents(filter);
        return this.<List<Event>>_createResponse(account)
                .setResponseData(events);
    }
}
