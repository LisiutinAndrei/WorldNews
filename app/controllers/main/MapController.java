package controllers.main;

import _infrastructure.ActionAuthenticator;
import com.fasterxml.jackson.core.JsonProcessingException;
import controllers.BaseController;
import models.business.main.MapBusinessLogic;
import models.domain.orm.Event;
import models.utils.infrastructurePackages.response.IResponsePackage;
import models.view.main.map.MapFilter;
import models.view.main.map.MapFilterRequest;
import play.data.Form;
import play.mvc.Result;
import play.mvc.Security;

import java.util.List;

import static play.data.Form.form;

public class MapController extends BaseController {

    @Security.Authenticated(ActionAuthenticator.class)
    public Result global() {
        Form<MapFilter> emptyForm = form(MapFilter.class);
        return ok(views.html.main.map.global.render(emptyForm));
    }

    @Security.Authenticated(ActionAuthenticator.class)
    public Result getEvents() throws JsonProcessingException {
        Form<MapFilterRequest> filterForm = Form.form(MapFilterRequest.class).bindFromRequest();
        MapFilterRequest filter = filterForm.get();
        IResponsePackage<List<Event>> resp = new MapBusinessLogic().getEvents(this._createRequestPackage()
                .setRequestData(filter));
        return ok(this._json(resp));
    }
}
