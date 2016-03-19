package controllers.main;

import _infrastructure.ActionAuthenticator;
import controllers.BaseController;
import models.business.main.EventBusinessLogic;
import models.domain.orm.Event;
import models.domain.orm.EventInstance;
import models.utils.exceptions.GenericApplicationException;
import models.utils.infrastructurePackages.response.IResponsePackage;
import play.data.Form;
import play.mvc.Result;
import play.mvc.Security;

import java.util.List;

import static play.data.Form.form;

public class EventController extends BaseController {

    @Security.Authenticated(ActionAuthenticator.class)
    public Result details(long eventID) {

        IResponsePackage<Event> resp = new EventBusinessLogic().getEventByID(this._createRequestPackage()
                .setRequestData(eventID));
        Event event = resp.getResponseData();
        Form<Event> eventForm = form(Event.class).fill(event);
        flash("coordsJson", this._json(event));
        return ok(views.html.main.event.details.render(eventForm));
    }

    @Security.Authenticated(ActionAuthenticator.class)
    public Result instance(long eventInstanceID) {

        IResponsePackage<EventInstance> resp = new EventBusinessLogic().getEventinstanceByID(this._createRequestPackage()
                .setRequestData(eventInstanceID));
        EventInstance event = resp.getResponseData();
        Form<EventInstance> eventForm = form(EventInstance.class).fill(event);
        flash("eventInstJson", this._json(event));
        return ok(views.html.main.event.instance.render(eventForm));
    }
}
