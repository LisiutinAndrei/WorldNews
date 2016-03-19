package models.business.main;

import models.business.BaseBusinessLogic;
import models.domain.main.ActorDomainLogic;
import models.domain.orm.Actor;
import models.utils.infrastructurePackages.Tuple;
import models.utils.infrastructurePackages.accountSession.IAccountSession;
import models.utils.infrastructurePackages.request.IRequestPackage;
import models.utils.infrastructurePackages.response.IResponsePackage;
import models.view.main.actor.ActorEventsFilter;
import models.view.main.actor.ActorFilter;

import java.util.List;

public class ActorBusinessLogic extends BaseBusinessLogic {
    public ActorBusinessLogic() {
        super();
    }

    public IResponsePackage<Actor> getActorByFilter(IRequestPackage<ActorEventsFilter> request) {
        IAccountSession account = request.getAccountSession();
        ActorEventsFilter filter = request.getRequestData();
        Actor actor = new ActorDomainLogic().getActorByFilter(filter);
        return this.<Actor>_createResponse(account)
                .setResponseData(actor);
    }

    public IResponsePackage<Tuple<List<Actor>, Integer>> getActorsList(IRequestPackage<ActorFilter> request) {
        IAccountSession account = request.getAccountSession();
        ActorFilter filter = request.getRequestData();
        Tuple<List<Actor>, Integer> actors = new ActorDomainLogic().getActorsList(filter);
        return this.<Tuple<List<Actor>, Integer>>_createResponse(account)
                .setResponseData(actors);
    }
}
