package controllers.main;

import controllers.BaseController;
import models.business.main.EventBusinessLogic;
import models.domain.orm.Event;
import models.utils.exceptions.GenericApplicationException;
import models.utils.infrastructurePackages.response.IResponsePackage;
import play.data.Form;
import play.mvc.Result;

import java.util.List;

import static play.data.Form.form;

public class EventController extends BaseController {

    public Result details(long eventID) {

        IResponsePackage<Event> resp = new EventBusinessLogic().getEventByID(this._createRequestPackage()
                .setRequestData(eventID));
        Event event = resp.getResponseData();
        Form<Event> eventForm = form(Event.class).fill(event);
        flash("coordsJson", this._json(event));
        return ok(views.html.main.event.details.render(eventForm));
    }
}
