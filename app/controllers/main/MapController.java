package controllers.main;

import controllers.BaseController;
import models.business.main.MapBusinessLogic;
import models.domain.orm.GeolocationPolygon;
import models.utils.infrastructurePackages.response.IResponsePackage;
import play.mvc.Result;

import java.util.List;

public class MapController extends BaseController {


    public Result global() {

        IResponsePackage<List<GeolocationPolygon>> resp = new MapBusinessLogic().getPolygons(this._createRequestPackage());

        return ok(views.html.main.map.global.render());
    }
}
