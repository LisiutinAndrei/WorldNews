package models.business.main;

import models.business.BaseBusinessLogic;
import models.domain.main.EventDomainLogic;
import models.domain.orm.Event;
import models.domain.orm.EventInstance;
import models.utils.infrastructurePackages.accountSession.IAccountSession;
import models.utils.infrastructurePackages.request.IRequestPackage;
import models.utils.infrastructurePackages.response.IResponsePackage;

public class EventBusinessLogic extends BaseBusinessLogic {
    public EventBusinessLogic() {
        super();
    }

    public IResponsePackage<Event> getEventByID(IRequestPackage<Long> request) {
        IAccountSession account = request.getAccountSession();
        long eventID = request.getRequestData();
       Event event = new EventDomainLogic().getEventByID(eventID);
        return this.<Event>_createResponse(account)
                .setResponseData(event);
    }

    public IResponsePackage<EventInstance> getEventinstanceByID(IRequestPackage<Long> request) {
        IAccountSession account = request.getAccountSession();
        long eventInstanceID = request.getRequestData();
       EventInstance event = new EventDomainLogic().getEventinstanceByID(eventInstanceID);
        return this.<EventInstance>_createResponse(account)
                .setResponseData(event);
    }
}
