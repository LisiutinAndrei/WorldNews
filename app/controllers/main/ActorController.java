package controllers.main;

import _infrastructure.ActionAuthenticator;
import controllers.BaseController;
import models.business.main.ActorBusinessLogic;
import models.domain.orm.Actor;
import models.utils.infrastructurePackages.Paging;
import models.utils.infrastructurePackages.Tuple;
import models.utils.infrastructurePackages.response.IResponsePackage;
import models.view.main.actor.ActorEventsFilter;
import models.view.main.actor.ActorEventsFilterResult;
import models.view.main.actor.ActorFilter;
import models.view.main.actor.ActorsPaged;
import play.data.Form;
import play.mvc.Result;
import play.mvc.Security;

import java.util.List;

import static play.data.Form.form;

public class ActorController extends BaseController {

    @Security.Authenticated(ActionAuthenticator.class)
    public Result details(long actorID) {
        Form<ActorEventsFilter> filterForm = form(ActorEventsFilter.class).bindFromRequest();
        ActorEventsFilter filter = filterForm.get();
        filter.actorID = actorID;
        IResponsePackage<Actor> resp = new ActorBusinessLogic().getActorByFilter(this._createRequestPackage()
                .setRequestData(filter));
        Actor actor = resp.getResponseData();
        flash("actorJson", this._json(actor));
        return ok(views.html.main.actor.details.render(new ActorEventsFilterResult(actor, filterForm)));
    }

    @Security.Authenticated(ActionAuthenticator.class)
    public Result list() {
        Form<ActorFilter> filterForm = form(ActorFilter.class).bindFromRequest();
        ActorFilter filter = filterForm.get();
        filter.paging = form(Paging.class).bindFromRequest().get();
        if (filter.paging == null || filter.paging.page < 1 || filter.paging.itemsPerPage < 1) {
            filter.paging = new Paging(1, 10);
        }
        IResponsePackage<Tuple<List<Actor>, Integer>> resp = new ActorBusinessLogic().getActorsList(this._createRequestPackage()
                .setRequestData(filter));
        Tuple<List<Actor>, Integer> actors = resp.getResponseData();
        ActorsPaged model = new ActorsPaged(actors.x, actors.y, filter.paging, filterForm);
        return ok(views.html.main.actor.list.render(model));
    }
}
