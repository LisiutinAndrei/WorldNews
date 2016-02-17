package controllers.main;

import controllers.BaseController;
import play.mvc.Result;

public class MapController extends BaseController {


    public Result global() {
        return ok(views.html.main.map.global.render());
    }
}
