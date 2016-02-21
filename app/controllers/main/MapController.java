package controllers.main;

import com.fasterxml.jackson.core.JsonProcessingException;
import controllers.BaseController;
import models.business.main.MapBusinessLogic;
import models.domain.orm.Event;
import models.utils.infrastructurePackages.response.IResponsePackage;
import play.mvc.Result;

import java.util.List;

public class MapController extends BaseController {


    public Result global() {

        IResponsePackage<List<Event>> resp = new MapBusinessLogic().getEvents(this._createRequestPackage());

        return ok(views.html.main.map.global.render());
    }

    public Result getEvents() throws JsonProcessingException {
        IResponsePackage<List<Event>> resp = new MapBusinessLogic().getEvents(this._createRequestPackage());
        return ok(this._json(resp));
    }
}
